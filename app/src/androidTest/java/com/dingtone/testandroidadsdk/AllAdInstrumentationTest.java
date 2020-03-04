package com.dingtone.testandroidadsdk;

import android.os.SystemClock;
import android.util.Log;

import com.dingtone.testandroidadsdk.activity.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

//import androidx.test.espresso.intent.Intents;
//import static androidx.test.ext.truth.content.IntentSubject.assertThat;


@RunWith(AndroidJUnit4.class)
public class AllAdInstrumentationTest {
    private final String TAG = "AllAdInstrumentationTest";

    @Before
    public void launchActivity() {
        ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void controlSwitch() {

    }



    @Test
    public void adProcessTest() {

        // start thread
        Log.d(TAG, "start test");

        // 先点击 MainActivity 上的 "全流程测试" 按钮
        onView(withId(R.id.btn_test)).perform(click());
        // 验证是否打开了 TestActivity
//        assertThat(Iterables.getOnlyElement(Intents.getIntents())).hasComponentClass(
//                TestActivity.class);
        // 在 TestActivity  输入 一个广告商34
        onView(withId(R.id.edit_ad_list)).perform(typeText("34"), closeSoftKeyboard());

        // 点击 保存  --》 校验toast
        onView(withId(R.id.btn_save)).perform(click());

//        onView(withText("配置广告链：")).inRoot(withDecorView(not(is(activityScenarioRule.getWindow().getDecorView())))).check(matches(isDisplayed()));
//        onView(withText("配置广告链： [34]")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));

//        onView(withText("test_tt")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));


        Log.d(TAG, "check toast");
        // 点击 banner
        onView(withId(R.id.btn_banner)).perform(click());

        SystemClock.sleep(5000);

        Log.d(TAG, "over");

        // define verify tag and logInfo
        ArrayList<String> tagLogInfoList = new ArrayList<>();
        tagLogInfoList.add("NativeAdLib@@onAdLoaded:  onAdLoaded");
        tagLogInfoList.add("NativeAdLib@@onLoadFailed: onLoadFailed : errorCode : 3");
        // verify log
        CheckLogs.checkLogs(tagLogInfoList, "@@");

    }


//    @Test
//    public void adProcessTest() {
//
//        // start thread
//        Log.d(TAG, "start test");
//
//        // 先点击 MainActivity 上的 "全流程测试" 按钮
//        onView(withId(R.id.btn_test)).perform(click());
//        // 验证是否打开了 TestActivity
////        assertThat(Iterables.getOnlyElement(Intents.getIntents())).hasComponentClass(
////                TestActivity.class);
//        // 在 TestActivity  输入 一个广告商39
//        onView(withId(R.id.edit_ad_list)).perform(typeText("34"), closeSoftKeyboard());
//
//        // 点击 保存  --》 校验toast
//        onView(withId(R.id.btn_save)).perform(click());
//
////        onView(withText("配置广告链：")).inRoot(withDecorView(not(is(activityScenarioRule.getWindow().getDecorView())))).check(matches(isDisplayed()));
////        onView(withText("配置广告链： [39]")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
//
////        onView(withText("test_tt")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
//
//
//        Log.d(TAG, "check toast");
//        // 点击 banner
//        onView(withId(R.id.btn_banner)).perform(click());
//
//        SystemClock.sleep(5000);
//
//        Log.d(TAG, "over");
//
//        final String verifyTag = "NativeAdLib";
//        final String logInfo1 = "onAdLoaded:  onAdLoaded";
//        final String logInfo2 = "onLoadFailed: onLoadFailed : errorCode : 3";
//
//        // test case verify log info
//        final String testCaseVerifyName1 = "verify log --> " + logInfo1;
//        final String testCaseVerifyName2 = "verify log --> " + logInfo2;
//
//        // define some code
//        final int FOUND_LOG_1 = 0;
//        final int NOT_FOUND_LOG_1 = 1;
//        final int FOUND_LOG_2 = 2;
//        final int NOT_FOUND_LOG_2 = 3;
//
//        // result msg mapping
//        HashMap<Integer, String> resultMsgMapping = new HashMap<>();
//        resultMsgMapping.put(FOUND_LOG_1, "check log --> " + logInfo1);
//        resultMsgMapping.put(NOT_FOUND_LOG_1, "not found log --> " + logInfo1);
//        resultMsgMapping.put(FOUND_LOG_2, "check log --> " + logInfo2);
//        resultMsgMapping.put(NOT_FOUND_LOG_2, "not found log --> " + logInfo2);
//
//        final ArrayList<String> logList = new ArrayList<>();
//
//        // init result code --> default not found match log
//        final HashMap<String, Integer> resultMap = new HashMap<>();
//        resultMap.put(testCaseVerifyName1, NOT_FOUND_LOG_1);
//        resultMap.put(testCaseVerifyName2, NOT_FOUND_LOG_2);
//
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Process mLogcatProc = null;
//                BufferedReader reader = null;
//                try {
//                    mLogcatProc = Runtime.getRuntime().exec("logcat -v time");
//                    reader = new BufferedReader(new InputStreamReader(mLogcatProc.getInputStream()));
//
//                    String line;
//                    int i = 0;
//                    while ((line = reader.readLine()) != null) {
//                        i += 1;
//                        // check log
//                        if (line.contains(verifyTag)) {
//                            if (line.contains(logInfo1)) {
//                                resultMap.put(testCaseVerifyName1, FOUND_LOG_1);
//                            }
//                            if (line.contains(logInfo2)) {
//                                resultMap.put(testCaseVerifyName2, FOUND_LOG_2);
//                            }
//                        }
//
//
//                        // filter log by tag
//                        if ((line.contains(verifyTag)) || (line.contains(logInfo1)) ||
//                                (line.contains(logInfo2))
//                        ) {
//                            logList.add(i + " --> " + line);
//                        }
//
//
//                        // get all log
//                        // logList.add(line);
//
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    Log.e(TAG, "get log error --> " + e.toString());
//                }
//            }
//        }).start();
//
//        SystemClock.sleep(2000);
//
//        Log.e(TAG, "s====================");
//        Log.e(TAG, logList.size() + " <-- size");
////        for (String log : logList) {
////            L.e(TAG, log);
////        }
//
//        for (int i = 0; i < logList.size(); i++) {
//            Log.e(TAG, i + " --> " + logList.get(i));
//        }
//
//        Log.e(TAG, "o====================");
//
//        Log.e(TAG, "verify start ====================");
//
//
//        int resultCodeSum = 0;
//        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
//            String mapKey = entry.getKey();
//            int resultCode = entry.getValue();
//            resultCodeSum += resultCode;
//            Log.e(TAG, "[ mapKey = " + mapKey + " ], [ mapValue = " + resultCode + " ]");
//        }
//
//        String resultMsg = "test case result : [ " + resultMsgMapping.get(resultMap.get(testCaseVerifyName1)) + " ], [ " +
//                resultMsgMapping.get(resultMap.get(testCaseVerifyName2)) + " ]";
//        Log.i(TAG, resultMsg);
//
//        Assert.assertEquals(resultMsg, FOUND_LOG_1 + FOUND_LOG_2, resultCodeSum);
//
//        Log.e(TAG, "verify over ====================");
//
//        SystemClock.sleep(2000);
//
//    }
}
