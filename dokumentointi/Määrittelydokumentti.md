**Käytettävät algoritmit ja tietorakenteet**

Mahdolliset algoritmit: timantti-neliö-algoritmia (diamond-square algorithm), Perlin-kohina, fraktionaalinen Brownin liike.
Tietorakenteista tarvitaan dynaaminen taulukko.

**Ongelma ja ratkaisumenetelmät**

Maastoa halutaan generoida dynaamisesti, niin että pelaajan liikkuessa pelissä aluetta generoidaan aluetta lisää.

Alkukartan generoimiseen voi käyttää timantti-neliö-algoritmia, mutta tällä tavoin tuotettu maasto pitäisi pitää muistissa. Tämä ei toimi, jos halutaan luoda loppumaton maasto, joka voi kasvaa äärettömästi.

Loppumattoman maaston luomiseen voidaan käyttää Perlin-kohinaa tai fraktionaalista Brownin liikettä, joilla saadaan tuotettua aina uudestaan samanlainen maasto alueelle, joka välillä joudutaan poistamaan muistista muistin säästämiseksi.

**Syötteet**

Syötteenä annetaan alueen alkuperäiskoko ja mahdollisesti jotain määrittelyjä maaston ominaisuuksille. Ehkä myös määritellään laajennusten koko.

**Aika- ja tilavaativuus**

Tilavaativuus on suoraan verrannollinen muistissa olevan alueen kokoon, eli alueen sisältämien pisteiden määrään, O(n^2) jossa n on alueen dimensio. 

**Lähteet**

Wikipedia: Diamond-square algorithm, Perlin noise
Kolmiulotteisen maaston generointi peleissä, Ilari Paananen
