package com.example.jewell

import com.example.jewell.models.Firm
import com.example.jewell.models.Product
import com.example.jewell.models.StockTaking
import com.example.jewell.models.Store
import java.time.LocalDate
import kotlin.random.Random

class DataSource{

    companion object {

        fun createProductsDataSet(): ArrayList<Product> {
            var rand = Random.nextInt(0, 100)
            val list = ArrayList<Product>()
            list.add(
                Product(
                    "Obraczka",
                    "21.54",
                    "https://png.pngtree.com/png-clipart/20190603/original/pngtree-jewelry-png-image_530814.jpg",
                    "Gold",
                    LocalDate.now(),
                    "White Gold +$rand",
                    585,
                    17.5,
                    18.5,
                    33.4,
                    44.71,
                        "Super shop 1",
                    "1",
                    51.94,
                    1746.9,
                    null,
                    Firm(
                        918267,
                        0,
                        "Diadema"
                    )
                )
            )
            list.add(
                Product(
                    "Kolczyki",
                    "91.52",
                    "https://png.pngtree.com/template/20190121/ourlarge/pngtree-cosmetic-and-jewelry-image_53158.jpg",
                    "Gold",
                    LocalDate.now(),
                    "Gold",
                    555,
                    11.5,
                    16.5,
                    32.1,
                    55.83,
                    "Super Shop 2",
                    "2",
                    51.94,
                    1746.9,
                    null,
                    Firm(
                        918267,
                        0,
                        "Diadema"
                    )
                )
            )

            list.add(
                Product(
                    "Pierscionek",
                    "13.42",
                    "https://png.pngtree.com/template/20190121/ourlarge/pngtree-cosmetic-and-jewelry-image_53173.jpg",
                    "White gold",
                    LocalDate.now(),
                    "Gold",
                    575,
                    14.5,
                    11.5,
                    36.1,
                    54.83,
                    "Super Shop 3",
                    "3",
                    51.94,
                    1746.9,
                    null,
                    Firm(
                        918267,
                        0,
                        "Diadema"
                    )
                )
            )
            list.add(
                Product(
                    "Zawieszka",
                    "75.18",
                    "https://png.pngtree.com/template/20190121/ourlarge/pngtree-cosmetic-and-jewelry-image_53169.jpg",
                    "Steven",
                    LocalDate.now(),
                    "Gold",
                    575,
                    14.5,
                    11.5,
                    36.1,
                    54.83,
                    "Super Shop 4",
                    "4",
                    51.94,
                    1746.9,
                    null,
                    Firm(
                        918267,
                        0,
                        "Diadema"
                    )
                )
            )
            list.add(
                Product(
                    "Bransoletka",
                    "97.53",
                    "https://png.pngtree.com/template/20190121/ourlarge/pngtree-cosmetic-and-jewelry-image_53153.jpg",
                    "Silver",
                    LocalDate.now(),
                    "Gold",
                    575,
                    14.5,
                    11.5,
                    36.1,
                    54.83,
                    "Super Shop 5",
                    "5",
                    51.94,
                    1746.9,
                    null,
                    Firm(
                        918267,
                        0,
                        "Diadema"
                    )
                )
            )
            list.add(
                Product(
                    "Naszyjnik",
                    "61.83",
                    "https://png.pngtree.com/template/20190121/ourlarge/pngtree-cosmetic-and-jewelry-image_53171.jpg",
                    "White Gold",
                    LocalDate.now(),
                    "Gold",
                    575,
                    14.5,
                    11.5,
                    36.1,
                    54.83,
                    "Super Shop 6",
                    "6",
                    51.94,
                    1746.9,
                    null,
                    Firm(
                        918267,
                        0,
                        "Diadema"
                    )
                )
            )
            list.add(
                Product(
                    "Wisiorek",
                    "80.17",
                    "https://png.pngtree.com/template/20190121/ourlarge/pngtree-cosmetic-and-jewelry-image_53151.jpg",
                    "Silver",
                    LocalDate.now(),
                    "Gold",
                    575,
                    14.5,
                    11.5,
                    36.1,
                    54.83,
                    "Super Shop 7",
                    "7",
                    51.94,
                    1746.9,
                    null,
                    Firm(
                        918267,
                        0,
                        "Diadema"
                    )
                )
            )
            list.add(
                Product(
                    "Kolczyki",
                    "78.98",
                    "https://png.pngtree.com/template/20190121/ourlarge/pngtree-cosmetic-and-jewelry-image_53151.jpg",
                    "White Gold",
                    LocalDate.now(),
                    "Gold",
                    575,
                    14.5,
                    11.5,
                    36.1,
                    54.83,
                    "Super Shop 8",
                    "8",
                    51.94,
                    1746.9,
                    null,
                    Firm(
                        918267,
                        0,
                        "Diadema"
                    )
                )
            )
            list.add(
                Product(
                    "Bransoletka",
                    "67.09",
                    "https://png.pngtree.com/template/20190121/ourlarge/pngtree-cosmetic-and-jewelry-image_53151.jpg",
                    "Gold",
                    LocalDate.now(),
                    "Gold",
                    575,
                    14.5,
                    11.5,
                    36.1,
                    54.83,
                    "Super Shop 9",
                    "9",
                    51.94,
                    1746.9,
                    null,
                    Firm(
                        918267,
                        0,
                        "Diadema"
                    )
                )
            )
            return list
        }

        fun createStoresDataSet(): List<Store> {
            val list = ArrayList<Store>()
            list.add(
                Store(
                    "https://www.cvabc.org/wp-content/uploads/stock-vector-store-front-strip-mall-stores-and-city-skyline-99826760.jpg",
                    "Warsaw",
                    1,
                    "01-234",
                    "Zlatodar",
                    ArrayList<Product>(),
                    LocalDate.now()
                )
            )
            list.add(
                Store(
                    "https://www.cvabc.org/wp-content/uploads/stock-vector-store-front-strip-mall-stores-and-city-skyline-99826760.jpg",
                    "Warsaw",
                    1,
                    "01-234",
                    "Zlatodar",
                    ArrayList<Product>(),
                    LocalDate.now()
                )
            )
            list.add(
                Store(
                    "https://www.cvabc.org/wp-content/uploads/stock-vector-store-front-strip-mall-stores-and-city-skyline-99826760.jpg",
                    "Warsaw",
                    1,
                    "01-234",
                    "Zlatodar",
                    ArrayList<Product>(),
                    LocalDate.now()
                )
            )
            list.add(
                Store(
                    "https://www.cvabc.org/wp-content/uploads/stock-vector-store-front-strip-mall-stores-and-city-skyline-99826760.jpg",
                    "Warsaw",
                    1,
                    "01-234",
                    "Zlatodar",
                    ArrayList<Product>(),
                    LocalDate.now()
                )
            )
            list.add(
                Store(
                    "https://www.cvabc.org/wp-content/uploads/stock-vector-store-front-strip-mall-stores-and-city-skyline-99826760.jpg",
                    "Warsaw",
                    1,
                    "01-234",
                    "Zlatodar",
                    ArrayList<Product>(),
                    LocalDate.now()
                )
            )
            list.add(
                Store(
                    "https://www.cvabc.org/wp-content/uploads/stock-vector-store-front-strip-mall-stores-and-city-skyline-99826760.jpg",
                    "Warsaw",
                    1,
                    "01-234",
                    "Zlatodar",
                    ArrayList<Product>(),
                    LocalDate.now()
                )
            )
            return list
        }

        fun createInventorizationsDataSet(): List<StockTaking> {
            val stores = createStoresDataSet()
            val stockTakings = ArrayList<StockTaking>()
            var i = 0
            for (store in stores) {
                val stockTaking = StockTaking(LocalDate.now(), null, i, stores[i])
                stockTakings.add(stockTaking)
                i++
            }
            return stockTakings
        }
    }

}