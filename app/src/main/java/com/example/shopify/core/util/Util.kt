package com.example.shopify.core.util

import java.util.StringTokenizer

/**
 * @Returns a pair of (size,color)
 * If the size or the color don't exist, a N/A is returned inside the pair instead.
 * If null is passed as an argument a pair of N/A(s) as strings is returned.
 * */
fun getVariantOptions(variantTitle: String?) : Pair<String,String>{
    if(variantTitle == null){
        return Pair("N/A","N/A")
    }
    val tokenizer = StringTokenizer(variantTitle," / ")
    val optionsList = mutableListOf<String>()

    while(tokenizer.hasMoreTokens()){
        optionsList.add(tokenizer.nextToken())
    }

    val optionsPair: Pair<String,String>

    if(optionsList.size == 2){
        optionsPair = Pair(optionsList[0],optionsList[1])
    }
    else{
        if(optionsList[0].toIntOrNull() == null){optionsPair = Pair("N/A",optionsList[0])}
        else{optionsPair = Pair(optionsList[0],"N/A")}
    }
    return optionsPair
}
