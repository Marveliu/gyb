---
layout: post
title: nutz-5-junit
date: 2017-11-14 15:05:41
tags: [nutz,junit]
categories: java开源框架学习
photos:
---

当我想写一个方法的时候，想确定它的正确性，如果是启动javaweb再进行测试的话，非常的废时间，如果可以提前进行测试就好了，这个时候就是要用junit

- 安装JDK，并配置好环境变量
- 工程已解决JUnit依赖关系（pom.xml）

IDEA自带一个JUnit插件，打开Settings窗口搜索junit。

该插件可以运行JUnit测试文件，但无法自动生成JUnit测试代码：

如果需要自动生成测试代码，需要安装JUnitGenerator V2.0

安装后，在需要测试的类文件里点击code->Generate，或者快捷键Alt+Insert，就会出现自动生成测试文件的选项。



.Properties选项卡里的Output Path为测试用例生成的目录(这个修改主要是针对maven工程的test文件夹跟src文件夹不一致)，修改为test目录：${SOURCEPATH}/../../test/java/${PACKAGE}/${FILENAME}



 4.切换到JUnit 4选项卡，可以修改生成测试用例的模板，比如类名、包名等，删掉模板的package 后面的test.  防止路径出错。



JUnit 是一个**回归测试框架**，被开发者用于实施对应用程序的单元测试，加快程序编制速度，同时提高编码的质量。JUnit 测试框架能够轻松完成以下任意两种结合：

- Eclipse 集成开发环境
- Ant 打包工具
- Maven 项目构建管理

特性：

- 测试工具
- 测试套件
- 测试运行器
- 测试分类

## 测试工具

**测试工具**是一整套固定的工具用于基线测试。测试工具的目的是为了确保测试能够在共享且固定的环境中运行，因此保证测试结果的可重复性。它包括：

- 在所有测试调用指令发起前的 setUp() 方法。
- 在测试方法运行后的 tearDown() 方法。

```
import junit.framework.*;

public class JavaTest extends TestCase {
   protected int value1, value2;

   // assigning the values
   protected void setUp(){
      value1=3;
      value2=3;
   }

   // test method to add two values
   public void testAdd(){
      double result= value1 + value2;
      assertTrue(result == 6);
   }
}
```

可以用来提前点赋值等操作。

## 测试套件

**测试套件**意味捆绑几个测试案例并且同时运行。在 JUnit 中，@RunWith 和 @Suite 都被用作运行测试套件。以下为使用 TestJunit1 和 TestJunit2 的测试分类：

```
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//JUnit Suite Test
@RunWith(Suite.class)
@Suite.SuiteClasses({ 
   TestJunit1.class ,TestJunit2.class
})
public class JunitTestSuite {
}
```

通过注解的形式，使得几个测试用例一起进行。

## 测试运行器

**测试运行器** 用于执行测试案例。以下为假定测试分类成立的情况下的例子：

```
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(TestJunit.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println(result.wasSuccessful());
   }
}
```



## JUnit 测试分类

**测试分类**是在编写和测试 JUnit 的重要分类。几种重要的分类如下：

- 包含一套断言方法的测试断言

- 包含规定运行多重测试工具的测试用例

- 包含收集执行测试用例结果的方法的测试结果

  ​

## JUnit 中的重要的 API

| 序号   | 类的名称       | 类的功能                      |
| ---- | ---------- | ------------------------- |
| 1    | Assert     | assert 方法的集合              |
| 2    | TestCase   | 一个定义了运行多重测试的固定装置          |
| 3    | TestResult | TestResult 集合了执行测试样例的所有结果 |
| 4    | TestSuite  | TestSuite 是测试的集合          |

Assert

| 序号   | 方法和描述                                    |
| ---- | ---------------------------------------- |
| 1    | **void assertEquals(boolean expected, boolean actual)** 检查两个变量或者等式是否平衡 |
| 2    | **void assertFalse(boolean condition)** 检查条件是假的 |
| 3    | **void assertNotNull(Object object)** 检查对象不是空的 |
| 4    | **void assertNull(Object object)** 检查对象是空的 |
| 5    | **void assertTrue(boolean condition)** 检查条件为真 |
| 6    | **void fail()** 在没有报告的情况下使测试不通过          |



TestCase

| 序号   | 方法和描述                                    |
| ---- | ---------------------------------------- |
| 1    | **int countTestCases()**为被run(TestResult result) 执行的测试案例计数 |
| 2    | **TestResult createResult()**创建一个默认的 TestResult 对象 |
| 3    | **String getName()**获取 TestCase 的名称      |
| 4    | **TestResult run()**一个运行这个测试的方便的方法，收集由TestResult 对象产生的结果 |
| 5    | **void run(TestResult result)**在 TestResult 中运行测试案例并收集结果 |
| 6    | **void setName(String name)**设置 TestCase 的名称 |
| 7    | **void setUp()**创建固定装置，例如，打开一个网络连接       |
| 8    | **void tearDown()**拆除固定装置，例如，关闭一个网络连接    |
| 9    | **String toString()**返回测试案例的一个字符串表示      |

## TestResult 类

| 序号   | 方法和描述                                    |
| ---- | ---------------------------------------- |
| 1    | **void addError(Test test, Throwable t)**在错误列表中加入一个错误 |
| 2    | **void addFailure(Test test, AssertionFailedError t)**在失败列表中加入一个失败 |
| 3    | **void endTest(Test test)**显示测试被编译的这个结果  |
| 4    | **int errorCount()**获取被检测出错误的数量          |
| 5    | **Enumeration errors()**返回错误的详细信息        |
| 6    | **int failureCount()**获取被检测出的失败的数量       |
| 7    | **void run(TestCase test)** 运行 TestCase  |
| 8    | **int int runCount()**获得运行测试的数量          |
| 9    | **void startTest(Test test)**声明一个测试即将开始  |
| 10   | **void stop()**标明测试必须停止                  |



## TestSuite 类

| 序号   | 方法和描述                                    |
| ---- | ---------------------------------------- |
| 1    | **void addTest(Test test)** 在套中加入测试。     |
| 2    | **void addTestSuite(Class<? extends TestCase> testClass)**将已经给定的类中的测试加到套中。 |
| 3    | **int countTestCases()**对这个测试即将运行的测试案例进行计数。 |
| 4    | **String getName()**返回套的名称。              |
| 5    | **void run(TestResult result)**在 TestResult 中运行测试并收集结果。 |
| 6    | **void setName(String name)**设置套的名称。     |
| 7    | **Test testAt(int index)**在给定的目录中返回测试。   |
| 8    | **int testCount()**返回套中测试的数量。            |
| 9    | **static Test warning(String message)**返回会失败的测试并且记录警告信息。 |