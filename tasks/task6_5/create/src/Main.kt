// Task 6.5: populating database tables using Exposed's SQL DSL
import org.jetbrains.exposed.v1.core.*
import org.jetbrains.exposed.v1.jdbc.*
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

fun main() {
    transaction(MusicDatabase.db) {
        // Start from empty tables

        SchemaUtils.drop(ArtistTable, AlbumTable)
        SchemaUtils.create(ArtistTable, AlbumTable)


        // Add at least three artists
        val bob=ArtistTable.insertAndGetId {
            it[artistName]="Bobson, Bobster"
            it[isSolo]=true
            it[info]="https://bobsterbobsontv.com"
        }

        val bob2=ArtistTable.insertAndGetId {
            it[artistName]="Bobson, Bobster2"
            it[isSolo]=true
            it[info]="https://bobsterbobsontv.io"
        }

        val bob3=ArtistTable.insertAndGetId {
            it[artistName]="Bobson, Bobster3"
            it[isSolo]=true
            it[info]="https://bobsterbobsontv.net"
        }

        // Add at least four albums
        AlbumTable.insert {
            it[title]="balls1"
            it[artist]=bob
            it[year]=1985
        }
        AlbumTable.insert {
            it[title]="balls2"
            it[artist]=bob2
            it[year]=1955
        }
        AlbumTable.insert {
            it[title]="balls3"
            it[artist]=bob3
            it[year]=2015
        }
        AlbumTable.insert {
            it[title]="back to the future"
            it[artist]=bob
            it[year]=2077
        }
    }
}
