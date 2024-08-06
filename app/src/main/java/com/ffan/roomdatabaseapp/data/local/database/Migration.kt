// MigrationHelper.kt
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Create the `animes` table with the correct schema
        database.execSQL(
            """
            CREATE TABLE `animes` (
                `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                `name` TEXT NOT NULL,
                `favChar` TEXT NOT NULL,
                `genre` TEXT NOT NULL,
                `rating` REAL NOT NULL
            )
            """.trimIndent()
        )
    }
}
