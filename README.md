# PowerDroid-ScriptLauncher

Serveur Java utilisé pour permettre le lancement du server web ainsi que la routine de chargement de fichiers à récupérer et à tester.

### Résumé

Cette partie du projet permet de faire la liaison entre la partie interface du projet et la partie test approprement parlé. Le serveur Java
a pour but de récupérer les informations de tests à effectuer sur une APK donné par l'utilisateur et d'effecter le lancement du script python
ainsi que le lancement simultanée des tests via [Robotium](http://robotium.com/) ou [Monkey](https://developer.android.com/studio/test/monkey.html).
Une fois cette analyze effectué, les résultats sont uploadés sur la DB au format CSV afin d'être affiché sur l'interface web.


### Technologies

PowerDroid-ScriptAnalyser a été développé en utilisant Java -J2E ainsi que les autres projets développés au cours de ce PFE qui disponible sur ces dépôts :

* [PowerDroid](https://github.com/Soulk/PowerDroid) - Serveur NodeJS permettant l'accès à l'interface web ainsi qu'au dépôt des fichiers nécessaires au lancement des tests et l'affichage de l'analyze correspondante
* [PowerDroid-ScriptAnalyser](https://github.com/decottis/PowerDroid-ScriptAnalyser) - Script python permettant l'utilisation et la récupération des données via le Power Monitor

### Installation

L'application nécessite l'installation de [Node](https://nodejs.org/en/) et d'une base de donnée [Postgresql](https://www.postgresql.org/) existante.

Récupération des sources du serveur.

```sh
$ git clone https://github.com/Soulk/PowerDroid.git
```

Installe les dépendances et start le serveur.

```sh
$ cd [votre repo]
$ npm install
$ npm start
```


