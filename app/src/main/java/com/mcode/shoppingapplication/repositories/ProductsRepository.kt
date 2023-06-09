package com.mcode.shoppingapplication.repositories

import com.mcode.shoppingapplication.models.ItemShop
import com.mcode.shoppingapplication.models.Product

class ProductsRepository {
    private val productList: List<ItemShop> = arrayListOf(
        ItemShop(Product(1, "Computador ASUS Vivobook 14 AMD ", "Computador portátil", 2200000), 0),
        ItemShop(Product(2, "Funda HP 14 negro", "Funda para computador portátil", 60000),0),
        ItemShop(Product(3, "Cargador original Apple tipo C carga rápida", "Cargador celular marca Apple", 110000),0),
        ItemShop(Product(4, "Mouse inalámbrico 200 negro HP", "Mouse inalámbrico gamer", 80000),0),
        ItemShop(Product(5, "Monitor Samsung ips 24 in Full HD", "Mónitor de 24 in", 750000),0),
        ItemShop(Product(6, "Disco de estado sólido Kingston ", "Diseñado para trabajar con computadoras Window o Mac", 400000),0),
        ItemShop(Product(7, "Audifonos Diadema 150 negro", "Almohadillas ultrasuaves y diadema acolchada. Diseño plegable", 250000),0),
        ItemShop(Product(8, "Tablet SAMSUNG GALAXY 10.5 in wifi", "Batería de larga duración, pantalla grande, buen rendimiento.", 900000),0)
    )

    fun getProductsList(): List<ItemShop> {
        return productList
    }
}