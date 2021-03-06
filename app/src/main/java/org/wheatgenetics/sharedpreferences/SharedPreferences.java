package org.wheatgenetics.sharedpreferences;

/**
 * Uses:
 * android.content.SharedPreferences
 * android.support.annotation.NonNull
 * android.util.Log
 *
 * org.wheatgenetics.sharedpreferences.UpdateVersionSharedPreferences
 *
 * org.wheatgenetics.inventory.model.Person
 */
public class SharedPreferences
extends org.wheatgenetics.sharedpreferences.UpdateVersionSharedPreferences
{
    private static final java.lang.String
        FIRST_NAME = "FirstName", LAST_NAME = "LastName", IGNORE_SCALE = "ignoreScale";

    // region Private Methods
    private static void sendDebugLogMsg(
    @android.support.annotation.NonNull final java.lang.String tag,
    @android.support.annotation.NonNull final java.lang.String msg)
    { android.util.Log.d("SharedPreferences." + tag, msg.equals("") ? "empty" : msg); }

    // region First Name Private Methods
    private java.lang.String getFirstName()
    {
        final java.lang.String firstName = this.getString(
            org.wheatgenetics.sharedpreferences.SharedPreferences.FIRST_NAME,
            /* validateKey => */true);
        org.wheatgenetics.sharedpreferences.SharedPreferences.sendDebugLogMsg(
            "getFirstName()", firstName);
        return firstName;
    }

    private void setFirstName(final java.lang.String firstName)
    {
        this.setString(org.wheatgenetics.sharedpreferences.SharedPreferences.FIRST_NAME,
            /* oldValue    => */ this.getFirstName(),
            /* newValue    => */ firstName          ,
            /* validateKey => */true);
        org.wheatgenetics.sharedpreferences.SharedPreferences.sendDebugLogMsg(
            "setFirstName()", this.getFirstName());
    }
    // endregion

    // region Last Name Private Methods
    private java.lang.String getLastName()
    {
        final java.lang.String lastName = this.getString(
            org.wheatgenetics.sharedpreferences.SharedPreferences.LAST_NAME,
            /* validateKey => */true);
        org.wheatgenetics.sharedpreferences.SharedPreferences.sendDebugLogMsg(
            "getLastName()", lastName);
        return lastName;
    }

    private void setLastName(final java.lang.String lastName)
    {
        this.setString(org.wheatgenetics.sharedpreferences.SharedPreferences.LAST_NAME,
            /* oldValue    => */ this.getLastName(),
            /* newValue    => */ lastName          ,
            /* validateKey => */true);
        org.wheatgenetics.sharedpreferences.SharedPreferences.sendDebugLogMsg(
            "setLastName()", this.getLastName());
    }
    // endregion
    // endregion

    public SharedPreferences(@android.support.annotation.NonNull
    final android.content.SharedPreferences sharedPreferences) { super(sharedPreferences); }

    @java.lang.Override protected void validateStringKey(
    @android.support.annotation.NonNull final java.lang.String key)
    {
        if (!key.equals(org.wheatgenetics.sharedpreferences.SharedPreferences.FIRST_NAME)
        &&  !key.equals(org.wheatgenetics.sharedpreferences.SharedPreferences.LAST_NAME ))
            super.validateStringKey(key);
    }

    // region Public Methods
    // region Person Public Methods
    public org.wheatgenetics.inventory.model.Person getPerson()
    {
        return new org.wheatgenetics.inventory.model.Person(
            this.getFirstName(), this.getLastName());
    }

    public boolean personIsSet() { return this.getPerson().isSet(); }

    public void setPerson(final org.wheatgenetics.inventory.model.Person person)
    {
        final java.lang.String firstName, lastName;
        if (null == person)
            firstName = lastName = null;
        else
            { firstName = person.firstName; lastName = person.lastName; }
        this.setFirstName(firstName); this.setLastName (lastName);
    }
    // endregion

    // region Name Public Method
    /**
     * A "safe" name is a full name (first name and last name) where the first name and last name
     * are separated with an underscore ("_") instead of a space (" ").
     */
    public java.lang.String getSafeName() { return this.getFirstName() + "_" + this.getLastName(); }
    // endregion

    // region IgnoreScale Public Methods
    public boolean getIgnoreScale()
    { return this.getBoolean(org.wheatgenetics.sharedpreferences.SharedPreferences.IGNORE_SCALE); }

    public void setIgnoreScaleToTrue()
    { this.setBooleanToTrue(org.wheatgenetics.sharedpreferences.SharedPreferences.IGNORE_SCALE); }
    // endregion
    // endregion
}