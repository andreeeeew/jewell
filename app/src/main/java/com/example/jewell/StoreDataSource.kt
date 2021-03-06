package com.example.jewell

import com.example.jewell.models.Product

class StoreDataSource{

    companion object{

        fun createDataSet(): ArrayList<Product>{
            val list = ArrayList<Product>()
            list.add(
                Product(
                    "Obraczka",
                    "You made it to the end of the course!\r\n\r\nNext we'll be building the REST API!",
                    "https://png.pngtree.com/png-clipart/20190603/original/pngtree-jewelry-png-image_530814.jpg",
                    "Gold",
                    "26.09.2018",
                    "White Gold",
                    585,
                    17.5,
                    18.5,
                    33.4,
                    44.71,
                    "Super shop 1",
                    "1234567891011",
                    51.94,
                    1746.9,
                    null
                )
            )
            list.add(
                Product(
                    "Kolczyki",
                    "The REST API course is complete. You can find the videos here: https://codingwithmitch.com/courses/build-a-rest-api/.",
                    "https://png.pngtree.com/template/20190121/ourlarge/pngtree-cosmetic-and-jewelry-image_53158.jpg",
                    "Gold",
                    "26.09.2018",
                    "Gold",
                    555,
                    11.5,
                    16.5,
                    32.1,
                    55.83,
                    "Super Shop 2",
                    "1234567891011",
                    51.94,
                    1746.9,
                    null
                )
            )

            list.add(
                Product(
                    "Pierscionek",
                    "Justin has been producing online courses for YouTube, Udemy, and his website CodingForEntrepreneurs.com for over 5 years.",
                    "https://png.pngtree.com/template/20190121/ourlarge/pngtree-cosmetic-and-jewelry-image_53173.jpg",
                    "White gold",
                    "26.09.2018",
                    "Gold",
                    575,
                    14.5,
                    11.5,
                    36.1,
                    54.83,
                    "Super Shop 3",
                    "1234567891011",
                    51.94,
                    1746.9,
                    null
                )
            )
            list.add(
                Product(
                    "Zawieszka",
                    "Vasiliy has been a freelance android developer for several years. He also has some of the best android development courses I've had the pleasure of taking on Udemy.com.",
                    "https://png.pngtree.com/template/20190121/ourlarge/pngtree-cosmetic-and-jewelry-image_53169.jpg",
                    "Steven",
                    "26.09.2018",
                    "Gold",
                    575,
                    14.5,
                    11.5,
                    36.1,
                    54.83,
                    "Super Shop 4",
                    "1234567891011",
                    51.94,
                    1746.9,
                    null
                )
            )
            list.add(
                Product(
                    "Bransoletka",
                    "Freelancing as an Android developer with Donn Felker.\\r\\n\\r\\nDonn is also:\\r\\n\\r\\n1) Founder of caster.io\\r\\n\\r\\n2) Co-host of the fragmented podcast (fragmentedpodcast.com).",
                    "https://png.pngtree.com/template/20190121/ourlarge/pngtree-cosmetic-and-jewelry-image_53153.jpg",
                    "Silver",
                    "26.09.2018",
                    "Gold",
                    575,
                    14.5,
                    11.5,
                    36.1,
                    54.83,
                    "Super Shop 5",
                    "1234567891011",
                    51.94,
                    1746.9,
                    null
                )
            )
            list.add(
                Product(
                    "Naszyjnik",
                    "What kind of hobbies do software developers have? It sounds like many software developers don't have a lot of hobbies and choose to focus on work. Is that a good idea?",
                    "https://png.pngtree.com/template/20190121/ourlarge/pngtree-cosmetic-and-jewelry-image_53171.jpg",
                    "White Gold",
                    "26.09.2018",
                    "Gold",
                    575,
                    14.5,
                    11.5,
                    36.1,
                    54.83,
                    "Super Shop 6",
                    "1234567891011",
                    51.94,
                    1746.9,
                    null
                )
            )
            list.add(
                Product(
                    "Wisiorek",
                    "In this podcast I interviewed the Fullsnack Developer (AKA Nicholas Olsen).\\r\\n\\r\\nNicholas is many things. What I mean by that is, he's good at many things.\\r\\n\\r\\n1. He’s an entrepreneur\\r\\n\\r\\n2. Web developer\\r\\n\\r\\n3. Artist\\r\\n\\r\\n4. Graphic designer\\r\\n\\r\\n5. Musician (drums)\\r\\n\\r\\n6. Professional BodyBuilder.",
                    "https://png.pngtree.com/template/20190121/ourlarge/pngtree-cosmetic-and-jewelry-image_53151.jpg",
                    "Silver",
                    "26.09.2018",
                    "Gold",
                    575,
                    14.5,
                    11.5,
                    36.1,
                    54.83,
                    "Super Shop 7",
                    "1234567891011",
                    51.94,
                    1746.9,
                    null
                )
            )
            list.add(
                Product(
                    "Kolczyki",
                    "Interviewing a web developer, javascript expert, entrepreneur, freelancer, podcaster, and much more.",
                    "https://png.pngtree.com/template/20190121/ourlarge/pngtree-cosmetic-and-jewelry-image_53151.jpg",
                    "White Gold",
                    "26.09.2018",
                    "Gold",
                    575,
                    14.5,
                    11.5,
                    36.1,
                    54.83,
                    "Super Shop 8",
                    "1234567891011",
                    51.94,
                    1746.9,
                    null
                )
            )
            list.add(
                Product(
                    "Bransoletka",
                    "Kaushik Gopal is a Senior Android Engineer working in Silicon Valley.\r\n\r\nHe works as a Senior Staff engineer at Instacart.\r\n\r\nInstacart: https://www.instacart.com/",
                    "https://png.pngtree.com/template/20190121/ourlarge/pngtree-cosmetic-and-jewelry-image_53151.jpg",
                    "Gold",
                    "26.09.2018",
                    "Gold",
                    575,
                    14.5,
                    11.5,
                    36.1,
                    54.83,
                    "Super Shop 9",
                    "1234567891011",
                    51.94,
                    1746.9,
                    null
                )
            )
            return list
        }
    }
}