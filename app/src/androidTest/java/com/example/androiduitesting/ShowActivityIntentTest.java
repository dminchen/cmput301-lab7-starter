package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ShowActivityIntentTest {

    @Rule
    public ActivityScenarioRule<MainActivity> rule =
            new ActivityScenarioRule<>(MainActivity.class);

    // 1) Check whether the activity correctly switched
    @Test
    public void testActivitySwitchesOnListClick() {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Calgary"), closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());

        onView(withText("Calgary")).perform(click()); // open ShowActivity
        onView(withId(R.id.city_name)).check(matches(isDisplayed()));
    }

    // 2) Test whether the city name is consistent
    @Test
    public void testCityNameConsistent() {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Toronto"), closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());

        onView(withText("Toronto")).perform(click());
        onView(withId(R.id.city_name)).check(matches(withText("Toronto")));
    }

    // 3) Test the "back" button
    @Test
    public void testBackButton() {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Edmonton"), closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());

        onView(withText("Edmonton")).perform(click());
        onView(withId(R.id.button_back)).perform(click());   // app's back
        onView(withText("Edmonton")).check(matches(isDisplayed()));

        // Launch again, then press system back
        onView(withText("Edmonton")).perform(click());
        pressBack();
        onView(withText("Edmonton")).check(matches(isDisplayed()));
    }
}
