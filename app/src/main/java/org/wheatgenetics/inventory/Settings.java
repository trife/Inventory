package org.wheatgenetics.inventory;

// Uses android.content.SharedPreferences.

class Settings extends java.lang.Object
{
    // region Constants
    static final private java.lang.String FIRST_NAME     = "FirstName"    ;
    static final private java.lang.String LAST_NAME      = "LastName"     ;
    static final private java.lang.String IGNORE_SCALE   = "ignoreScale"  ;
    static final private java.lang.String UPDATE_VERSION = "UpdateVersion";
    // endregion


    protected android.content.SharedPreferences sharedPreferences;


    // region Protected Methods
    protected java.lang.String getString(final java.lang.String key)
    {
        assert key != null;
        assert key.equals(org.wheatgenetics.inventory.Settings.FIRST_NAME)
            || key.equals(org.wheatgenetics.inventory.Settings.LAST_NAME );

        assert this.sharedPreferences != null;
        return this.sharedPreferences.getString(key, "");
    }

    protected void setString(final java.lang.String key,
    final java.lang.String oldValue, java.lang.String newValue)
    {
        assert oldValue != null;
        if (newValue == null) newValue = "";
        if (!oldValue.equals(newValue))
        {
            assert key != null;
            assert key.equals(org.wheatgenetics.inventory.Settings.FIRST_NAME)
                || key.equals(org.wheatgenetics.inventory.Settings.LAST_NAME );

            assert this.sharedPreferences != null;
            final android.content.SharedPreferences.Editor editor = this.sharedPreferences.edit();

            assert editor != null;
            editor.putString(key, newValue);
        }
    }

    protected int getUpdateVersion()
    {
        assert this.sharedPreferences != null;
        return this.sharedPreferences.getInt(
            org.wheatgenetics.inventory.Settings.UPDATE_VERSION, -1);
    }
    // endregion


    // region Package Methods
    Settings(final android.content.SharedPreferences sharedPreferences)
    {
        super();
        assert sharedPreferences != null;
        this.sharedPreferences = sharedPreferences;
    }


    // region FirstName Methods
    java.lang.Boolean firstNameIsSet() { return this.getFirstName().length() > 0; }

    java.lang.String getFirstName()
    {
        return this.getString(org.wheatgenetics.inventory.Settings.FIRST_NAME);
    }
    // endregion


    // region LastName Method
    java.lang.String getLastName()
    {
        return this.getString(org.wheatgenetics.inventory.Settings.LAST_NAME);
    }
    // endregion


    // region Name Methods
    void setName(final java.lang.String firstName, final java.lang.String lastName)
    {
        this.setString(org.wheatgenetics.inventory.Settings.FIRST_NAME,
            this.getFirstName(), firstName);
        this.setString(org.wheatgenetics.inventory.Settings.LAST_NAME,
            this.getLastName(), lastName);


        assert this.sharedPreferences != null;
        final android.content.SharedPreferences.Editor editor = this.sharedPreferences.edit();

        assert editor != null;
        editor.apply();
    }

    java.lang.String getName() { return this.getFirstName() + " " + this.getLastName(); }

    /**
     * A "safe" name is a full name (first name and last name) where the first name and last name
     * are separated with an underscore ("_") instead of a space (" ").
     */
    java.lang.String getSafeName() { return this.getFirstName() + "_" + this.getLastName(); }
    // endregion


    // region ignoreScale Methods
    java.lang.Boolean getIgnoreScale()
    {
        assert this.sharedPreferences != null;
        return this.sharedPreferences.getBoolean(
            org.wheatgenetics.inventory.Settings.IGNORE_SCALE, false);
    }

    void setIgnoreScaleToTrue()
    {
        if (!this.getIgnoreScale())
        {
            assert this.sharedPreferences != null;
            final android.content.SharedPreferences.Editor editor = this.sharedPreferences.edit();

            assert editor != null;
            editor.putBoolean(org.wheatgenetics.inventory.Settings.IGNORE_SCALE, true);
            editor.apply();
        }
    }
    // endregion


    // region UpdateVersion Methods
    java.lang.Boolean updateVersionIsSet(final int newUpdateVersion)
    {
        return this.getUpdateVersion() >= newUpdateVersion;
    }

    void setUpdateVersion(final int newUpdateVersion)
    {
        if (this.getUpdateVersion() != newUpdateVersion)
        {
            assert this.sharedPreferences != null;
            final android.content.SharedPreferences.Editor editor = this.sharedPreferences.edit();

            assert editor != null;
            editor.putInt(org.wheatgenetics.inventory.Settings.UPDATE_VERSION, newUpdateVersion);
            editor.apply();
        }
    }
    // endregion
    // endregion
}