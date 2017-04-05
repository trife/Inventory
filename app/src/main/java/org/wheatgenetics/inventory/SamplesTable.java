package org.wheatgenetics.inventory;

// Uses android.content.ContentValues, android.content.Context, android.database.Cursor,
//  android.database.sqlite.SQLiteDatabase, and android.database.sqlite.SQLiteOpenHelper.

class SamplesTable extends android.database.sqlite.SQLiteOpenHelper {
    // region Private Constants
    // region Database Constants
    private static final int              DATABASE_VERSION = 3            ;
    private static final java.lang.String DATABASE_NAME    = "InventoryDB";
    // endregion


    // region Table Constants
    private static final java.lang.String TABLE_NAME = "samples";

    private static final java.lang.String ID_FIELD_NAME       = "id"      ;
    private static final java.lang.String BOX_FIELD_NAME      = "box"     ;
    private static final java.lang.String ENVID_FIELD_NAME    = "envid"   ;
    private static final java.lang.String PERSON_FIELD_NAME   = "person"  ;
    private static final java.lang.String DATE_FIELD_NAME     = "date"    ;
    private static final java.lang.String POSITION_FIELD_NAME = "position";
    private static final java.lang.String WT_FIELD_NAME       = "wt"      ;
    // endregion
    // endregion


    SamplesTable(final android.content.Context context)
    {
        super(
            /* context => */ context                                                  ,
            /* name    => */ org.wheatgenetics.inventory.SamplesTable.DATABASE_NAME   ,
            /* factory => */ null                                                     ,
            /* version => */ org.wheatgenetics.inventory.SamplesTable.DATABASE_VERSION);
    }


    // region Overridden Methods
    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db)
    {
        assert db != null;
        db.execSQL("CREATE TABLE " + this.TABLE_NAME + " ( "                  +
            this.ID_FIELD_NAME       + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            this.BOX_FIELD_NAME      + " TEXT, "                              +
            this.ENVID_FIELD_NAME    + " TEXT, "                              +
            this.PERSON_FIELD_NAME   + " TEXT, "                              +
            this.DATE_FIELD_NAME     + " TEXT, "                              +
            this.POSITION_FIELD_NAME + " TEXT, "                              +
            this.WT_FIELD_NAME       + " TEXT)");
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion)
    {
        assert db != null;
        db.execSQL("DROP TABLE IF EXISTS " + this.TABLE_NAME);
        this.onCreate(db);
    }
    // endregion


    static private android.content.ContentValues makeContentValues(
    final org.wheatgenetics.inventory.InventoryRecord inventoryRecord)
    {
        assert inventoryRecord != null;

        final android.content.ContentValues contentValues = new android.content.ContentValues();

        contentValues.put(org.wheatgenetics.inventory.SamplesTable.BOX_FIELD_NAME,
            inventoryRecord.getBox());
        contentValues.put(org.wheatgenetics.inventory.SamplesTable.ENVID_FIELD_NAME,
            inventoryRecord.getEnvId());
        contentValues.put(org.wheatgenetics.inventory.SamplesTable.PERSON_FIELD_NAME,
            inventoryRecord.getPerson());
        contentValues.put(org.wheatgenetics.inventory.SamplesTable.DATE_FIELD_NAME,
            inventoryRecord.getDate());
        contentValues.put(org.wheatgenetics.inventory.SamplesTable.POSITION_FIELD_NAME,
            inventoryRecord.getPosition());
        contentValues.put(org.wheatgenetics.inventory.SamplesTable.WT_FIELD_NAME,
            inventoryRecord.getWt());

        return contentValues;
    }

    // region Protected Methods
    protected int internalDelete(final java.lang.String whereClause)
    {
        return this.getWritableDatabase().delete(this.TABLE_NAME, whereClause, null);
    }

    protected org.wheatgenetics.inventory.InventoryRecord get(final int id)
    {
        final org.wheatgenetics.inventory.InventoryRecord inventoryRecord =
            new org.wheatgenetics.inventory.InventoryRecord();
        {
            final java.lang.String[] FIELD_NAMES = {
                org.wheatgenetics.inventory.SamplesTable.ID_FIELD_NAME      ,
                org.wheatgenetics.inventory.SamplesTable.BOX_FIELD_NAME     ,
                org.wheatgenetics.inventory.SamplesTable.ENVID_FIELD_NAME   ,
                org.wheatgenetics.inventory.SamplesTable.PERSON_FIELD_NAME  ,
                org.wheatgenetics.inventory.SamplesTable.DATE_FIELD_NAME    ,
                org.wheatgenetics.inventory.SamplesTable.POSITION_FIELD_NAME,
                org.wheatgenetics.inventory.SamplesTable.WT_FIELD_NAME      };
            final android.database.Cursor cursor = this.getReadableDatabase().query(
                /* table         => */ this.TABLE_NAME                                      ,
                /* columns       => */ FIELD_NAMES                                          ,
                /* selection     => */ " " + this.ID_FIELD_NAME + " = ?"                    ,
                /* selectionArgs => */ org.wheatgenetics.inventory.Utils.makeStringArray(id),
                /* groupBy       => */ null                                                 ,
                /* having        => */ null                                                 ,
                /* orderBy       => */ null                                                 ,
                /* limit         => */ null                                                 );

            if (cursor != null) {
                cursor.moveToFirst();
                inventoryRecord.set(
                    /* id       => */ cursor.getString(0),
                    /* box      => */ cursor.getString(1),
                    /* envid    => */ cursor.getString(2),
                    /* person   => */ cursor.getString(3),
                    /* date     => */ cursor.getString(4),
                    /* position => */ cursor.getString(5),
                    /* wt       => */ cursor.getString(6));
                cursor.close();
            }
        }
        inventoryRecord.sendDebugLogMsg("get(" + id + ")");
        return inventoryRecord;
    }

    protected int update(final org.wheatgenetics.inventory.InventoryRecord inventoryRecord)
    {
        int i;
        {
            final android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
            {
                assert inventoryRecord != null;

                final int                           id            = inventoryRecord.getId();
                final android.content.ContentValues contentValues =
                    org.wheatgenetics.inventory.SamplesTable.makeContentValues(inventoryRecord);

                contentValues.put(this.ID_FIELD_NAME, id);
                i = db.update(this.TABLE_NAME, contentValues, this.ID_FIELD_NAME + " = ?",
                    org.wheatgenetics.inventory.Utils.makeStringArray(id));
            }
            db.close();
        }
        return i;
    }
    // endregion


    // region Package Methods
    // region Single-Record Package Methods
    void add(final org.wheatgenetics.inventory.InventoryRecord inventoryRecord)
    {
        assert inventoryRecord != null;
        inventoryRecord.sendDebugLogMsg("add()");

        final android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();

        db.insert(this.TABLE_NAME, null,
            org.wheatgenetics.inventory.SamplesTable.makeContentValues(inventoryRecord));
        db.close();
    }

    java.lang.Boolean delete(final org.wheatgenetics.inventory.InventoryRecord inventoryRecord)
    {
        assert inventoryRecord != null;
        inventoryRecord.sendDebugLogMsg("delete()");
        return this.internalDelete(
            this.POSITION_FIELD_NAME + "='" + inventoryRecord.getPositionAsString() + "'") > 0;
    }
    // endregion


    // region Multiple-Record Package Methods
    org.wheatgenetics.inventory.InventoryRecords getAll()
    {
        final org.wheatgenetics.inventory.InventoryRecords inventoryRecords =
            new org.wheatgenetics.inventory.InventoryRecords();
        {
            final android.database.Cursor cursor = this.getWritableDatabase().rawQuery(
                "SELECT * FROM " + this.TABLE_NAME, null);

            if (cursor.moveToFirst())
                do
                    inventoryRecords.add(new org.wheatgenetics.inventory.InventoryRecord(
                        /* id       => */ cursor.getString(0),
                        /* box      => */ cursor.getString(1),
                        /* envid    => */ cursor.getString(2),
                        /* person   => */ cursor.getString(3),
                        /* date     => */ cursor.getString(4),
                        /* position => */ cursor.getString(5),
                        /* wt       => */ cursor.getString(6)));
                while (cursor.moveToNext());
            cursor.close();
        }
        inventoryRecords.sendDebugLogMsg("getAll()");
        return inventoryRecords;
    }

    java.lang.String getBoxList()
    {
        java.lang.String boxList = null;
        {
            final android.database.Cursor cursor = this.getWritableDatabase().query(
                /* distinct => */ true           ,
                /* table    => */ this.TABLE_NAME,
                /* columns  => */
                    org.wheatgenetics.inventory.Utils.makeStringArray(this.BOX_FIELD_NAME),
                /* selection     => */ null               ,
                /* selectionArgs => */ null               ,
                /* groupBy       => */ this.BOX_FIELD_NAME,
                /* having        => */ null               ,
                /* orderBy       => */ null               ,
                /* limit         => */ null               );

            if (cursor.moveToFirst())
            {
                java.lang.String box = cursor.getString(0);
                while (box == null)
                    if (cursor.moveToNext())
                        box = cursor.getString(0);
                    else
                        break;

                if (box != null)
                {
                    boxList = "'" + box + "'";
                    while (cursor.moveToNext()) boxList += ",'" + cursor.getString(0) + "'";
                    boxList = "(" + boxList + ")";
                }
            }
            cursor.close();
        }
        return boxList;
    }

    void deleteAll() { this.internalDelete(null); }
    // endregion
    // endregion
}