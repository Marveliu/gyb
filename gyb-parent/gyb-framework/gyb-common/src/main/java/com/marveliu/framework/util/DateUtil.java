package com.marveliu.framework.util;
/*
 * Copyright [2018] [Marveliu]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.commons.lang3.time.DateFormatUtils;
import org.nutz.lang.Times;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Marveliu
 * @since 04/05/2018
 **/

public class DateUtil {
    private static final Locale DEFAULT_LOCALE = Locale.CHINA;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前时间(HH:mm:ss)
     *
     * @return
     */
    public static String getDate() {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd", DEFAULT_LOCALE);
    }

    /**
     * 获取当前时间(HH:mm:ss)
     *
     * @return
     */
    public static String getTime() {
        return DateFormatUtils.format(new Date(), "HH:mm:ss", DEFAULT_LOCALE);
    }

    /**
     * 获取当前时间(yyyy-MM-dd HH:mm:ss)
     *
     * @return
     */
    public static String getDateTime() {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss", DEFAULT_LOCALE);
    }

    /**
     * 转换日期格式(yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        if (date == null) return "";
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss", DEFAULT_LOCALE);
    }

    /**
     * 转换日期格式(yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @param f
     * @return
     */
    public static String format(Date date, String f) {
        if (date == null) return "";
        return DateFormatUtils.format(date, f, DEFAULT_LOCALE);
    }

    /**
     * 时间戳日期
     *
     * @param time
     * @return
     */
    public static String getDate(long time) {
        return DateFormatUtils.format(new Date(time * 1000), "yyyy-MM-dd HH:mm:ss", DEFAULT_LOCALE);
    }



    /**
     * 时间戳日期
     *
     * @param time
     * @param f
     * @return
     */
    public static String getDate(long time, String f) {
        return DateFormatUtils.format(new Date(time * 1000), f, DEFAULT_LOCALE);
    }

    /**
     * 通过字符串时间获取时间戳 nutzwk5.0改为long
     *
     * @param date
     * @return
     */
    public static int getTime(String date) {
        try {
            return (int) (Times.parse(sdf, date).getTime() / 1000);
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * 获得当前时间戳
     *
     * @return
     */
    public static int getCurrentTime() {
        try {
            return (int) (Times.parse(sdf, getDateTime()).getTime() / 1000);
        } catch (ParseException e) {
            return 0;
        }
    }


    /**
     * 通过字符串时间获取时间戳 nutzwk5.0改为long
     *
     * @param date
     * @return
     */
    public static int getTime(SimpleDateFormat sdf, String date) {
        try {
            return (int) (Times.parse(sdf, date).getTime() / 1000);
        } catch (ParseException e) {
            return 0;
        }
    }
}