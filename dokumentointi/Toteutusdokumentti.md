**Ohjelman yleisrakenne**

Maastogeneraattorissa käytetään timantti-neliö- tai perlinkohina-algoritmia maaston luomiseen. Näille on omat luokkansa. Algoritmit luovat maaston, joka tallennetaan korkeusarvoina taulukkoon.

Taulukkoon tallennettu maasto pitää saada myös muihin muotoihin graafista esitystä varten. Tähän tarkoitukseen on Maailma-luokka, jossa korkeusarvojen taulukko muutetaan mm. vektoriesitykseksi ja kolmioiduksi pinnaksi, ja maastoon lisätään värit ja valonlähteestä aiheutuvat varjot.

Grafiikkaluokat auttavat maaston visualisoinnissa. RGB-luokka auttaa värien määrittelyssä ja itse Grafiikkaluokka hoitaa maaston piirtämisen ruudulle.

Laskennassa tarvitaan vektoreita, kolmioita ja kvaternioita, joilla on omat luokkansa ja niissä erilaisia metodeja laskutoimituksille.

**Aika- ja tilavaatimukset**

Perlinkohinaan perustuvan maastogeneraattorin aikavaativuus on maaston pisteiden määrän n suhteen O(n). Tilavaativuus on vakio eli O(1), sillä muistissa tarvitsee pitää vain vakiomäärä pisteitä, joka on paljon vähemmän kuin koko mahdollisen maaston pisteiden määrä.

Timantti-neliö-algoritmin aikavaativuus on samoin O(n) ja tilavaativuus O(n), sillä kaikki maaston pisteet säilytetään muistissa.

**Työn puutteet ja parannusehdotukset**



**Lähteet**

https://en.wikipedia.org/wiki/Diamond-square_algorithm
https://en.wikipedia.org/wiki/Perlin_noise
