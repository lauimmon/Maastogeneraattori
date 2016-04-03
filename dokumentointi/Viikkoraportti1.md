**Ensimmäinen viikko**

Päätin ottaa aiheeksi maastogeneroinnin. Aihetta tutkiessa löysin ensin timantti-neliö-algoritmin (diamond-square algorithm). Pian selvisi, että tämä soveltuu hyvin rajatun alueen luomiseen, mutta jos haluan luoda maastoa näennäisesti rajattomaan maailmaan, ei tätä algoritmia voi käyttää. Rajaton maailma on sellainen, jota ei luoda kaikkea kerralla, vaan sitä generoituu sitä mukaa kun esim. pelissä pelaaja liikkuu uusille alueille. Kaikkea ei voi säilyttää muistissa, mutta pelaajan palatessa paikkaan joka on jo muistista poistettu, pitäisi maasto luoda tasan samanlaisena kuin aiemmin. 

Rajattoman maailman luomista selvitellessäni törmäsin Perlin-kohinaan (Perlin noise) ja fraktionaaliseen Brownin liikkeeseen (fractal Brownian noise). Toisin kuin helposti ymmärrettävä timantti-neliö-algoritmi, näiden periaatteet eivät heti selvinneet. Sen opin Pelin-kohinasta, että sillä voi luoda maastoon myös luolastoja, toisin kuin timantti-neliö-algoritmilla, jolla luodussa maastossa joka pistettä vastaa vain yksi korkeusarvo.

**Toinen viikko**

**23.3.**

Tein timantti-neliö-algoritmin valmiiksi Javalla, ja Matlabilla plottasin generoituja maastoja. Maastoista tuli vuoristoisempia kuin odotin, eikä jyrkkyysparametrin muuttaminen näyttänyt vaikuttavan huomattavasti kuin hyvin pienillä arvoilla, < 0.1. Jos nurkat asetti samanarvoisiksi, maasto näytti aina yhtä jyrkältä. Maksimi ja minimiarvot kyllä muuttuivat jyrkkyysparametria muuttaessa, mutta pisteiden väliset erot näyttivät yhtä jyrkiltä. Ehkä maastojen eroja ei kyennyt näkemään kun ne olivat eri koordinaatistoissa. Vierekkäin samassa koordinaatistossa niitä voisi olla helpompi vertailla.

Perlin-kohinaan en ehtinyt vielä perehtyä kunnolla, se on seuraavana tehtävänä. Työmäärä tänään 7,5 tuntia.

**30.3.**

Luin lisää Perlin-kohinasta sekä Simplex-kohinasta (Simplex noise). Lisäsin kommentit timantti-neliö-koodiin. Testailin taas algoritmia eri arvoilla. Ehkä jyrkkyyteen saisi selkeämpiä eroja, ja maastot saisi luonnollisemmiksi lisäämällä maastoon eroosiota. Tein ohjelmaan testejä. Työmäärä tänään neljä tuntia.

**31.3.**

Lisää lueskelua Perlin-kohinasta, Simplex-kohinasta ja fraktionaalisesta Brownin liikkeestä. Pohdiskelua siitä, mitä näistä kannattaisi koodissa toteuttaa ja onko ylipäätään realistista toteuttaa yhtä enempää. Luin myös miten eroosion voisi toteuttaa, ja kannattaako se. Rajattomassa maailmassa ilmeisesti ei. Työmäärä 2,5 tuntia.

**1.4.**

Luettavaa löytyi tänään Value noisesta, joka on hyvin paljon Perlin-kohinan kaltaista. Aloitin Perlin-kohinan koodaamisen. Työmäärä tänään 6,5 tuntia.

**3.4.**


