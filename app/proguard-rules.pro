# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

-keepattributes SourceFile,LineNumberTable
-dontwarn java.util.**
-dontwarn java.time.**
-dontwarn javax.**
-dontwarn com.sun.tools.**
-dontwarn com.sun.misc.**
-dontwarn com.sun.misc.Unsafe
-dontwarn com.sun.source.**
-dontwarn com.squareup.**
-dontwarn com.google.auto.**
-dontwarn com.google.common.**
-dontwarn java.lang.ClassValue
-dontwarn com.google.j2objc.**
-dontwarn butterknife.**
-dontskipnonpubliclibraryclasses
#retrolambda
-dontwarn java.lang.invoke.*
-dontwarn **$$Lambda$*
#butterknife
-keep class butterknife.*
-keep public class * implements butterknife.Unbinder { public <init>(**, android.view.View); }
-keepclasseswithmembernames class * { @butterknife.* <methods>; }
-keepclasseswithmembernames class * { @butterknife.* <fields>; }

-keep class org.adw.library.widgets.discreteseekbar.* { *; }
-keep class com.google.common.* { *; }
-keep class com.kcode.lib.* { *; }
-dontwarn com.kcode.lib.**
-keep class com.google.common.collect.Count { int value; }
-dontwarn com.thebluealliance.spectrum.**
#joda-time
-dontwarn org.joda.convert.**
-dontwarn org.joda.time.**
-keep class org.joda.time.** { *; }
-keep interface org.joda.time.** { *; }

