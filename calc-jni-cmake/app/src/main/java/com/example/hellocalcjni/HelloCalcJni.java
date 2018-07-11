/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.hellocalcjni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HelloCalcJni extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Retrieve our TextView and set its content.
         * the text is retrieved by calling a native
         * function.
         */
        setContentView(R.layout.activity_hello_jni);
        String lineSeparator = System.getProperty("line.separator");
        StringBuilder stbuilder = new StringBuilder();
        stbuilder.append(stringFromJNI());
        stbuilder.append(lineSeparator);
        stbuilder.append(lineSeparator);
        stbuilder.append(unimplementedStringFromJNI());
        stbuilder.append(lineSeparator);
        stbuilder.append(lineSeparator);
        stbuilder.append("add:  12 + 3 = " + add(12,3));
        stbuilder.append(lineSeparator);
        stbuilder.append(lineSeparator);
        stbuilder.append("sub:  12 - 3 = " + sub(12,3));
        stbuilder.append(lineSeparator);
        stbuilder.append(lineSeparator);
        stbuilder.append("mul:  12 x 3 = " + mul(12,3));
        stbuilder.append(lineSeparator);
        stbuilder.append(lineSeparator);
        stbuilder.append("div:  12 % 3 = " + div(12,3));
        TextView tv = (TextView)findViewById(R.id.hello_textview);
        tv.setText(stbuilder.toString());
    }
    /* A native method that is implemented by the
     * 'hello-jni' native library, which is packaged
     * with this application.
     */
    public native String  stringFromJNI();

    /* This is another native method declaration that is *not*
     * implemented by 'hello-jni'. This is simply to show that
     * you can declare as many native methods in your Java code
     * as you want, their implementation is searched in the
     * currently loaded native libraries only the first time
     * you call them.
     *
     * Trying to call this function will result in a
     * java.lang.UnsatisfiedLinkError exception !
     */
    public native String  unimplementedStringFromJNI();

    public native int add(int n1, int n2);

    public native int sub(int n1, int n2);

    public native int mul(int n1, int n2);

    public native int div(int n1, int n2);

    /* this is used to load the 'hello-jni' library on application
     * startup. The library has already been unpacked into
     * /data/data/com.example.hellojni/lib/libhello-jni.so at
     * installation time by the package manager.
     */
    static {
        System.loadLibrary("calc-jni");
    }
}
