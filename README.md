# PowerDroid-ScriptLauncher

Serveur Java utilisé pour permettre le lancement du server web ainsi que la routine de chargement de fichiers à récupérer et à tester.

### Résumé

Cette partie du projet permet de faire la liaison entre la partie interface du projet et la partie test approprement parlé. Le serveur Java
a pour but de récupérer les informations de tests à effectuer sur une APK donné par l'utilisateur et d'effecter le lancement du script python
ainsi que le lancement simultanée des tests via [Robotium](http://robotium.com/) ou [Monkey](https://developer.android.com/studio/test/monkey.html).
Une fois cette analyse effectué, les résultats sont uploadés sur la DB au format CSV afin d'être affiché sur l'interface web.


### Technologies

PowerDroid-ScriptAnalyser a été développé en utilisant Java -J2E ainsi que les autres projets développés au cours de ce PFE qui disponible sur ces dépôts :

* [PowerDroid](https://github.com/Soulk/PowerDroid) - Serveur NodeJS permettant l'accès à l'interface web ainsi qu'au dépôt des fichiers nécessaires au lancement des tests et l'affichage de l'analyse correspondante
* [PowerDroid-ScriptAnalyser](https://github.com/decottis/PowerDroid-ScriptAnalyser) - Script python permettant l'utilisation et la récupération des données via le Power Monitor

### Installation

L'application nécessite l'installation simplement un JDK 1.8 ainsi que les drivers de connexion JDBC/PostgreSQL.

Récupération des sources du serveur.

```sh
$ git clone https://github.com/Soulk/PowerDroid-ScriptLauncher.git
```

Lancement de la classe Java

```sh
$ Main.java
```

### Utilisation

L'utilisation se fait simplement au lancement, le serveur est ensuite près à effectuer toutes ses tâches de manière automatique. 
