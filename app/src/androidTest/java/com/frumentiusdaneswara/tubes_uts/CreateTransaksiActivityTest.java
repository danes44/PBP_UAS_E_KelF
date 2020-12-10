package com.frumentiusdaneswara.tubes_uts;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreateTransaksiActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void createTransaksiActivityTest() throws InterruptedException {

        Thread.sleep(2000);

        ViewInteraction textInputEditText = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.inputEmail),
                                0),
                        1),
                        isDisplayed()));
        textInputEditText.perform(replaceText("daneswaranandiwardana@gmail.com"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.inputPassword),
                                0),
                        1),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("daneswara44"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.btnSignIn),
                        childAtPosition(
                                allOf(withId(R.id.layoutBawah),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                3)));
        materialButton.perform(scrollTo(), click());

        Thread.sleep(5000);

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.employee_rv),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                2)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));


        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.btn_transaksi),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.btnBook), withText("BOOK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.edit_namapemasan),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twnama),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText3.perform(click());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.edit_namapemasan),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twnama),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(replaceText("joko"), closeSoftKeyboard());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.btnBook), withText("BOOK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.edit_nohppemesan),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twnohp),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText5.perform(click());

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.edit_nohppemesan),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twnohp),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText6.perform(replaceText("647264"), closeSoftKeyboard());


        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.edit_nohppemesan), withText("647264"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twnohp),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText7.perform(replaceText("64726473"));

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.edit_nohppemesan), withText("64726473"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twnohp),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText8.perform(closeSoftKeyboard());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.btnBook), withText("BOOK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1),
                        isDisplayed()));
        materialButton6.perform(click());

        ViewInteraction textInputEditText9 = onView(
                allOf(withId(R.id.edit_metode_pembayaran),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twmetode),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText9.perform(click());

        ViewInteraction textInputEditText10 = onView(
                allOf(withId(R.id.edit_metode_pembayaran),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twmetode),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText10.perform(replaceText("Credit"), closeSoftKeyboard());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.btnBook), withText("BOOK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1),
                        isDisplayed()));
        materialButton7.perform(click());

        ViewInteraction textInputEditText11 = onView(
                allOf(withId(R.id.edit_hargakos),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twharga),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText11.perform(click());

        ViewInteraction textInputEditText12 = onView(
                allOf(withId(R.id.edit_hargakos),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.twharga),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText12.perform(replaceText("99999"), closeSoftKeyboard());

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.btnBook), withText("BOOK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        5),
                                1),
                        isDisplayed()));
        materialButton8.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
