package dev.mateuy.katas.aoc.twentytwo

import dev.mateuy.katas.sensei.CodeLine
import dev.mateuy.katas.sensei.demonstrate
import java.nio.file.Path
import kotlin.io.path.readLines
import kotlin.io.path.readText

fun main() {
    val home = System.getProperty("user.home")
    demonstrate(path = Path.of("$home/teaching/teachingsite/source/CodeYourOwnAdventure/aoc/2022_1.md")){

        text {
            """# Advent of Code - dia 1 
Ja tenim el primer problema de'[Advent of Code 2022](https://adventofcode.com/2022/day/1), espero que 
l'hageu resolt (sinó no, no mireu aquest document: encara teniu temps de fer-lo!). 
Per si ja l'heu fet us explico una forma elegant de fer-lo amb Kotlin amb una mica de programació funcional.

## Part 1
En la primera part hem de calcular la suma total de calories de l'elf que porta més calories.

### Lectura fitxer
Per llegir el fitxer, creem un Path i carreguem tot el contingut a una variable String (input) amb _readText()_. En aquesta explicació faré servir les dades de l'exemple de l'enunciat.""".trimMargin()
        }



        codeNextLine()
        val input = Path.of("src/main/resources/day1.input").readText()
        output {
            input.replace("\n", "\\n")// Print only
        }


        text{
            """### Llista d'elfs
                |El següent pas serà convertir l'String en una llista, on cada cel·la tindrà la informació de cada elf. 
                |El doble salt de línia ("\n\n") ens serveix de separador entre els diferents elfs i la funció split crea una llista d'un String utilitzant un separador:""".trimMargin()
        }
        codeAndOutput{
            input.split("\n\n")
        }
        text{
            """### Matriu d'elfs
                |Ara volem convertir la llista d'strings en una llista de llistes on cada fila serà un elf, i cada cel·la un String amb les calories d'un aliment.
                |Per cada un dels elfs (cel·les) podem crear una llista d'aliments amb la funció _lines()_.
                | Això ho hem de fer per cada un dels elfs: hem de transformar cada cel·la en una llista. Farem servir la funció _map()_ que transforma cada element
                | amb l'acció que li passem per paràmetre.
            """.trimMargin()
        }
        codeAndOutput{
            input.split("\n\n").map{it.lines()}
        }
        text{
            """### Llista de calories
                |Ara volem tenir per cada elf la suma de les seves calories. Per cada elf tenim una llista de strings, 
                |que podem sumar amb la funció _sumOf_, indicant-li com sumem els String.
                | Per sumar un String, només l'hem de convertir a int amb _toInt()_.
                | 
                |Ja tenim una llista de enters on cada cel·la és la suma total de les calories que porta cada elf.""".trimMargin()
        }
        codeNextLine()
        val elves = input.split("\n\n").map{it.lines().sumOf { it.toInt() }}
        output{
            elves
        }

        text{
            """### Resultat
                |Ara només ens queda obtenir el màxim dels enters que tenim. La funció max ens serà d'ajuda""".trimMargin()
        }
        codeAndOutput{
            elves.max()
        }
        text{
            "El resultat és 72478, que és el que indica l'enunciat. Ho enviem i ja tenim una estrella!"
        }
        text{
            """## Part 2
                |En la segona part necessitem obtenir els tres elfs amb més calories totals i sumar-los. 
                |A l'apartat anterior ja tenim una llista amb els totals de caloríes de cada elf.
                |Necessitem:
                |- Ordenar-los: _sortedDescending()_ ens ordena la llista començant pel més gran
                |- Agafar els tres primers: _take(3)_ afaga els 3 primes elements
                |- Sumar-los: sum() suma la llista de tres enters que ens queda
            """.trimMargin()
        }
        codeAndOutput {
            elves.sortedDescending()
        }
        codeAndOutput {
            elves.sortedDescending().take(3)
        }
        codeAndOutput {
            elves.sortedDescending().take(3).sum()
        }
        text{
            "El resultat és 210367, que és el que indica l'enunciat. Ho enviem i ja tenim dues estrelles!"
        }
    }
}