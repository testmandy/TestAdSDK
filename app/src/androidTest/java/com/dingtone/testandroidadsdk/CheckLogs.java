package com.dingtone.testandroidadsdk;

import android.os.SystemClock;
import android.util.Log;

import org.junit.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckLogs {
    private static final String TAG = "CheckLogs";

    /**
     * check logs
     *
     * @param tagLogInfoList eg: ["tag1@@log1", "tag3@@log3", "tag3@@log3",]
     * @param regex          eg: @@
     */
    static void checkLogs(ArrayList<String> tagLogInfoList, String regex) {
        final List<String> tagList = new ArrayList<>();
        final List<String> logInfoList = new ArrayList<>();
        final List<String> testStepVerifyNameList = new ArrayList<>();
        final ArrayList<String> logList = new ArrayList<>();

        final int FOUND_LOG__BASE_CODE = 0;
        final int NOT_FOUND_LOG_BASE_CODE = 200;
        int RESULT_CODE_SUM_BASE = 0;

        final HashMap<String, Integer> resultCodeDefaultMap = new HashMap<>();
        HashMap<Integer, String> resultMsgMapping = new HashMap<>();

        for (int i = 0; i < tagLogInfoList.size(); i++) {
            Log.d(TAG, "define some mapping");
            RESULT_CODE_SUM_BASE += i;

            String[] tagLogInfoArray = tagLogInfoList.get(i).split(regex);
            String tag = tagLogInfoArray[0];
            String logInfo = tagLogInfoArray[1];

            tagList.add(tag);
            logInfoList.add(logInfo);

            String testStepVerifyName = i + " step verify log --> " + logInfo;
            testStepVerifyNameList.add(testStepVerifyName);

            int FOUND_LOG_CODE = i + FOUND_LOG__BASE_CODE;
            int NOT_FOUND_LOG_CODE = i + NOT_FOUND_LOG_BASE_CODE;

            resultCodeDefaultMap.put(testStepVerifyName, NOT_FOUND_LOG_CODE);

            resultMsgMapping.put(FOUND_LOG_CODE, i + " check log --> " + logInfo);
            resultMsgMapping.put(NOT_FOUND_LOG_CODE, i + " not found log --> " + logInfo);
        }

        Log.d(TAG, "my log ss========");
        Log.d(TAG, tagList.size() + "");
        Log.d(TAG, logInfoList.size() + "");
        Log.d(TAG, testStepVerifyNameList.size() + "");
        Log.d(TAG, logList.size() + "");
        Log.d(TAG, resultCodeDefaultMap.size() + "");
        Log.d(TAG, resultMsgMapping.size() + "");
        Log.d(TAG, "my log oo========");

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "logcat start working");
                Process logcatProcess = null;
                BufferedReader reader = null;
                try {
                    logcatProcess = Runtime.getRuntime().exec("logcat -v time");
                    reader = new BufferedReader(new InputStreamReader(logcatProcess.getInputStream()));

                    String line;
                    int i = 0;
                    while ((line = reader.readLine()) != null) {
                        i += 1;

                        // check log
                        for (int j = 0; j < tagList.size(); j++) {
                            String tag = tagList.get(j);
                            String logInfo = logInfoList.get(j);

                            int FOUND_LOG_CODE = j + FOUND_LOG__BASE_CODE;
                            String testStepVerifyName = testStepVerifyNameList.get(j);

                            if (line.contains(tag) && line.contains(logInfo)) {
                                resultCodeDefaultMap.put(testStepVerifyName, FOUND_LOG_CODE);
                            }

                            // [only debug] --> collect log by tag and logInfo
                            if (line.contains(tag) || line.contains(logInfo)) {
                                logList.add(i + " --> " + line);
                            }
                        }


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, "get log error --> " + e.toString());
                }
            }
        }).start();

        // waiting
        SystemClock.sleep(2000);

        Log.e(TAG, "s====================");
        Log.e(TAG, logList.size() + " <-- size");
        for (int i = 0; i < logList.size(); i++) {
            Log.e(TAG, i + " --> " + logList.get(i));
        }
        Log.e(TAG, "o====================");


        Log.e(TAG, "verify start ====================");
        int resultCodeSum = 0;
        StringBuilder resultMsgBuffer = new StringBuilder("test case result : ");

        for (Map.Entry<String, Integer> entry : resultCodeDefaultMap.entrySet()) {
            String mapKey = entry.getKey();
            int resultCode = entry.getValue();
            resultCodeSum += resultCode;
            Log.e(TAG, "[ mapKey = " + mapKey + " ], [ mapValue = " + resultCode + " ]");
            // collect result msg
            resultMsgBuffer.append("[").append(resultMsgMapping.get(resultCode)).append("], ");
        }

        Log.i(TAG, resultMsgBuffer.toString());
        // verify result
        Assert.assertEquals(resultMsgBuffer.toString(), (RESULT_CODE_SUM_BASE + tagLogInfoList.size() * FOUND_LOG__BASE_CODE), resultCodeSum);

        Log.e(TAG, "verify over ====================");

    }

}
