---
.layout: post
title: nutz-3-dao
date: 2017-11-11 15:47:20
tags: [nutz,dao]
categories: java开源框架学习
photos:
---

Nutz集成提供了 BeetlViewMaker ，实现了 ViewMaker方法，如下代码

[Nutz集成](http://ibeetl.com/guide/#beetl_4_9)

```java
@At("/ctx")
@Ok("beetl:ctx.btl")
public Context withContext() {
        Context ctx = Lang.context();
        Pager pager = dao.createPager(1, 20);
        pager.setRecordCount(dao.count(UserProfile.class));
        List<UserProfile> list = dao.query(UserProfile.class, null, pager);
        ctx.set("pager", pager);
        ctx.set("list", list);
        return ctx;
}

<html>
<head>
<title>Beetl&Nutz</title>
</head>
<body>
<p>总共 ${list.~size}<p/>
<%
for(user in list){
%>
<p>hello,${user.nickname};<p/>
<% } %>

<p>当前页${pager.pageNumber},总共${pager.pageCount}页<p/>
</body>
</html>
```

需要注意的是，如果使用了nutz的obj（<http://www.nutzam.com/core/mvc/view.html>），则需要在模板顶部申明obj是动态对象，如

```
<%
directive dynamic obj
%>

${obj.user.title}
${obj.user.name}
```

或者使用beetl的默认引擎，采取如下配置

ENGINE=org.beetl.core.engine.DefaultTemplateEngine



# 映射关系

## 一对一映射

`@One`

```java
@Table("t_pet")
public class Pet extends Pojo {

    @Column
    public int masterId;

    @One(field = "masterId")
  	//@One(field = "masterId", key = "id") 在一对一映射中，有可能会存在两边对应字段名称不同的情况，所以可以通过附加 key,此列中target对象的主键为id
    // 1.r.59之前需要写target参数
    // @One(target = Master.class, field = "masterId")
    public Master master;

}
```

- POJO 类中**必须**存在一个属性，本 POJO 将通过该属性同目标 [POJO 类的主键](http://www.nutzam.com/core/dao/primary_key.html) 关联
- 该属性必须同目标 POJO （Master）的主键类型相同
- **注意**，这里是大小写敏感的

### 插入操作

```java
Pet pet = new Pet();
pet.setName("XiaoBai");
Master master = new Master();
master.setName("Peter");
pet.setMaster(master);

//dao可以将pet和它对应的master一起插入数据库
dao.insertWith(pet,"master");
```

Nutz.Dao 会根据正则表达式 "master" 寻找可以被匹配上的映射字段（只要声明了 @One, @Many, @ManyMany 任何一个注解，都是映射字段） 并根据注解具体的配置信息，执行相应的 SQL。

```java
//这里因为是一对一映射，所以会首先插入映射对象，以便用新的主键值更新主对象的映射字段
//对象中包括多个 @One 字段，被你的正则式匹配上，那么这些字段对应的字段（如果不为null）都会被匹配，并首先被插入
dao.insertWith(pet,"master");
执行 SQL : INSERT INTO t_master (name) VALUES("Peter");
执行 SQL 获取 最大值： SELECT MAX(id) FROM t_master  // 假设返回的值是 29 过 SELECT MAX 来获取插入的最大值
将该最大值 29 赋给 master 对象的主键 id
将该最大值 29 赋给 pet.masterId 字段
执行 SQL : INSERT INTO t_pet (name,masterId) VALUES("Xiaobai",29)
   
//仅仅插入映射的字段，不会插入pet对象 
dao.insertLinks(pet,"master");
执行 SQL : INSERT INTO t_master (name) VALUES("Peter");
执行 SQL 获取 最大值： SELECT MAX(id) FROM t_master  // 假设返回的值是 29
将该最大值 29 赋给 master 对象的主键 id
```

### 获取操作

```java
Pet pet = dao.fetch(Pet.class, "XiaoBai");
dao.fetchLinks(pet, "master");

执行 SQL: SELECT * FROM t_pet WHERE name='XiaoBai';  // 如果 pet.masterId 是 29
执行 SQL: SELECT * FROM t_master WHERE id=29;j
```

### 更新操作

```java
dao.updateWith(pet, "master");

执行SQL: UPDATE t_master ....
执行SQL: UPDATE t_pet ...
  
dao.updateLinks(pet, "master");
执行SQL: UPDATE t_master ....
```

### 删除操作

```java
//全部删除
dao.deleteWith(pet, "master");

//仅删除master
dao.deleteLinks(pet, "master");

//清楚master
dao.clearLinks(pet, "master");
```

## 一对多映射

`@many`

```java

//在一的一方进行配置
//要求和之前的一样
@Table("t_master")
public class Master extends Pojo {
  
  	@Many(field = "masterId")
    // 1.r.59之前需要写target参数
    // @Many(target = Pet.class, field = "masterId")
    private List<Pet> pets;
  
    public List<Pet> getPets() {
        return pets;
    }
    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
```

## 操作

> 因为和之前的一对一大多数相同，因此主要贴代码。

```java
Master master = new Master();
master.setName("Peter");

List<Pet> pets = new ArrayList<Pet>();
pets.add(new Pet("XiaoBai");
pets.add(new Pet("XiaoHei");

master.setPets(pets);
         
         
dao.insertWith(master, "pets");
执行 SQL : INSERT INTO t_master (name) VALUES("Peter");
执行 SQL 获取 最大值： SELECT MAX(id) FROM t_master  // 假设返回的值是 29
将该最大值 29 赋给 master 对象的主键 id
循环 master.pets，将该最大值 29 赋给每一个 pet 对象的 pet.masterId 字段
执行 SQL : INSERT INTO t_pet (name,masterId) VALUES("XiaoBai",29)
执行 SQL : INSERT INTO t_pet (name,masterId) VALUES("XiaoHei",29)

dao.insertLinks(master,"pets");
循环 master.pets，将该master.id (主键) 赋给每一个 pet 对象的 pet.masterId 字段，我们假设该值为 29
执行 SQL : INSERT INTO t_pet (name,masterId) VALUES("XiaoBai",29)
执行 SQL : INSERT INTO t_pet (name,masterId) VALUES("XiaoHei",29)
         
Master master = dao.fetch(Master.class, "Peter");
dao.fetchLinks(master, "pets");         
执行 SQL: SELECT * FROM t_master WHERE name='Peter'; // 如果 master.id 是 12
执行 SQL: SELECT * FROM t_pet WHERE masterId=12;         
         
dao.updateWith(master, "pets");
执行SQL: UPDATE t_master ....
循环 master.pets 并依次执行SQL: UPDATE t_pet ...         
dao.updateLinks(master, "pets");  
循环 master.pets 并依次执行SQL: UPDATE t_pet .
         
         
//全部删除
dao.deleteWith(pet, "master");
//仅删除master
dao.deleteLinks(pet, "master");
//清楚master
dao.clearLinks(pet, "master"); 
清除同删除的区别在于，清除只会执行一条 SQL 删除一批映射对象，而且删除会逐个调用 dao.delete 来删除对象 
```

## 多对多映射

`@ManyMany`



- relation 为中间数据表的表名，它也支持[动态表名](http://www.nutzam.com/core/dao/dynamic_table_name.html)
- from 是中间数据表的字段名，这个字段将储存主对象的主键（下例的 Food 的主键）
- to 是中间数据表的字段名，这个字段将储存映射对象的主键（下例的 Pet 的主键）

```java
@Table("t_food")
public class Food extends Pojo {

    @ManyMany(relation = "t_pet_food", from = "foodid", to = "petid")
    // 1.r.59之前需要写target参数
    // @ManyMany(target = Pet.class, relation = "t_pet_food", from = "foodid", to = "petid")
    private List<Pet> pets;

    public List<Pet> getPets() {
        return pets;
    }
    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
```

- 数据库必须存在一个中间表 t_pet_food
  - 该表有一个字段 foodid 对应到 Food 对象的主键
  - 该表有一个字段 petid 对应到 Pet 对象的主键
- Nutz.Dao 通过 @ManyMany 这四个属性了解到：
  - 目标的 POJO 类 ： Pet
  - 关联表（或者说：中间表）：t_pet_food
  - 关联表的 foodid 字段对应到是本 POJO （Food）主键
  - 关联表的 petid 字段对应到是目标 POJO （Pet） 主键



```java
//我们有两个 POJO
public class Pet {
    @Id
    public int id;
    @Name
    public String name;
}
//-------------------------------
public class Food {
    @Id
    public int id;
    @Name
    public String name;
}

t_pet_food
===================
 pid | fid
-----+------
 3   | 6
 9   | 8
  
//添加@ManyMany
public class Pet {
    @Id
    public int id;
    @Name
    public String name;
    @ManyMany(relation="t_pet_food", 
            from="pid",
            to="fid")
    public List<Food> foods;
}


//告诉 NutDao 请用 Pet.name 来映射这个 pid 字段
//to 也有一样的属性。并且冒号后面的并不用一定是 PK 字段，只要是惟一性字段均可
@ManyMany(relation="t_pet_food", 
            from="pid:name", 
            to="fid")
public List<Food> foods;

//@Many也可以应用在
//数组
//Map
//POJO

dao.insertWith(food, "pets");
执行 SQL : INSERT INTO t_food (name) VALUES("Fish");
执行 SQL 获取 最大值： SELECT MAX(id) FROM t_food  // 假设返回的值是 6
循环 food.pets
    执行 SQL: INSERT INTO t_pet (name) VALUES("XiaoBai");
    执行 SQL 获取 最大值： SELECT MAX(id) FROM t_pet  // 假设返回的值是 97
    执行 SQL 插入关联: INSERT INTO t_pet_food (foodid, petid) VALUES(6, 97);
    ...
      
dao.insertLinks(food,"pets");
循环 food.pets
    执行 SQL: INSERT INTO t_pet (name) VALUES("XiaoBai");
    执行 SQL 获取 最大值： SELECT MAX(id) FROM t_pet  // 假设返回的值是 97
    执行 SQL 插入关联: INSERT INTO t_pet_food (foodid, petid) VALUES(6, 97);
    ...

//已经存在了 food 和 pets 对象，你仅仅打算将它们关联起来
dao.insertRelation(food,"pets");
//如果 food.id 的值为 6
循环 food.pets
    执行 SQL 插入关联: INSERT INTO t_pet_food (foodid, petid) VALUES(6, 97);
    ...
      
Food food = dao.fetch(Food.class, "Fish");
dao.fetchLinks(food, "pets");
执行 SQL: SELECT * FROM t_food WHERE name='Fish'; // 如果 food.id 是6
执行 SQL: SELECT * FROM t_pet WHERE id IN (SELECT petid FROM t_pet_food WHERE foodid=6)
  

dao.updateWith(food, "pets");
dao.updateLinks(food, "pets");

dao.deleteWith(food, "pets");
dao.deleteLinks(food, "pets");
dao.clearLinks(food, "pets");

删除不仅会删掉 t_pet_food 里的记录，还会逐个调用 dao.delete 来删除 pet 对象。
而清除只会执行一条 SQL 来删除 t_pet_food 中的记录（即中间表中的记录），但是 t_pet 和 t_food 表中的数据不会被删除。
```





# 表结构迁移

老板说要每个用户新增加个phone字段,咋搞? 自定义SQL执行一条ALTER吗? 不不不,自动迁移一下表结构就好了.

```
Daos.migration(User.class, true, false, false);

```

第一个参数是类,第二个参数是否新增字段,第三个参数是否删除字段(通常为false),第4个参数是否检查索引(通常为false).

额,那很多很多pojo呢? 也能批量迁移吗? 可以的

```
Daos.migration(dao, "net.wendal.nutzbook.bean", true, false, false);
```

# 事务



# BaseServiceImpl

尤其要注意的是，你的pojo是否注释了`@view`，如果注释了一切的查询都会走视图，因此单独注册一个视图的pojo比较有用。



## count

```java
public int count(Condition cnd) {
    return this.dao().count(this.getEntityClass(), cnd);
}

public int count() {
    return this.dao().count(this.getEntityClass());
}
```



## fetch



```java

//通过主键查询对象
public T fetch(int id) {   //还可以是String...
    return this.dao().fetch(this.getEntityClass(), id);
}

//通过条件
public T fetch(Condition cnd) {
    return dao().fetch(getEntityClass(), cnd);
}
```

### fetchlinks



```java
//数据对象,可以是普通对象或集合,但不是类
//为null查询全部,支持通配符 ^(a|b)$
public <T> T fetchLinks(T obj, String regex) {
    return this.dao().fetchLinks(obj, regex);
}

//cnd 查询条件
public <T> T fetchLinks(T obj, String regex, Condition cnd)
```



## Insert





```java

//T可以是集合对象，数组等，但是要求里面的元素类型是一致的
public <T> T insert(T obj) {
    return this.dao().insert(obj);
}

//添加过滤
public <T> T insert(T obj, FieldFilter filter) {
    return this.dao().insert(obj, filter);
}

//想查询再更新，T只能是对象，依据对象的主键
public <T> T insertOrUpdate(T obj) {
        return this.dao().insertOrUpdate(obj);
}

//添加过滤
public <T> T insertOrUpdate(T obj, FieldFilter insertFieldFilter, FieldFilter updateFieldFilter) {
        return this.dao().insertOrUpdate(obj, insertFieldFilter, updateFieldFilter);
    }


//自由的向一个数据表插入一条数据
public void insert(String tableName, Chain chain) {
        this.dao().insert(tableName, chain);
    }



    /**
     * 将对象插入数据库同时，也将符合一个正则表达式的所有关联字段关联的对象统统插入相应的数据库
     * <p>
     * 关于关联字段更多信息，请参看 '@One' | '@Many' | '@ManyMany' 更多的描述
     *
     * @param obj   数据对象
     * @param regex 正则表达式，描述了什么样的关联字段将被关注。如果为 null，则表示全部的关联字段都会被插入
     * @return 数据对象本身
     * @see org.nutz.dao.entity.annotation.One
     * @see org.nutz.dao.entity.annotation.Many
     * @see org.nutz.dao.entity.annotation.ManyMany
     */
public <T> T insertWith(T obj, String regex) {
        return this.dao().insertWith(obj, regex);
    }


    /**
     * 根据一个正则表达式，仅将对象所有的关联字段插入到数据库中，并不包括对象本身
     *
     * @param obj   数据对象
     * @param regex 正则表达式，描述了什么样的关联字段将被关注。如果为 null，则表示全部的关联字段都会被插入
     * @return 数据对象本身
     * @see org.nutz.dao.entity.annotation.One
     * @see org.nutz.dao.entity.annotation.Many
     * @see org.nutz.dao.entity.annotation.ManyMany
     */
public <T> T insertLinks(T obj, String regex) {
        return this.dao().insertLinks(obj, regex);
    }
```



## Update



```java
//根据主键查询一个数据
public int update(Object obj) {
    return this.dao().update(obj);
}

//更新数据忽略值为null的字段
public int updateIgnoreNull(Object obj) {
    return this.dao().updateIgnoreNull(obj);
}


//更新部分实体
public int update(Chain chain, Condition cnd) {
    return this.dao().update(this.getEntityClass(), chain, cnd);
}

updateWith
updateLinks
updateRelation

//基于更新
updateWithVersion
```



## Delete



```java
//通过主键来删除
public int delete(int id) {
    return this.dao().delete(this.getEntityClass(), id);
}


//批量删除
public void delete(Integer[] ids)
  
  
  

public int vDelete(String[] ids) {
	return this.dao().update(this.getEntityClass(), Chain.make("delFlag", true),Cnd.where("id", "in", ids));
    }
```



## 常用技巧



批量更新数据



```java
for (String s : ids) {
    if (!Strings.isEmpty(s)) {
        libTaskService.insert("lib_task_skill", org.nutz.dao.Chain.make("taskId", taskid).add("skillId", s));
    }
}
```



Cnd

filter

chain