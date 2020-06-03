![Conecta4](https://github.com/anderct105/conecta4/blob/feature/readme/src/main/resources/imagenes/Conecta4_Logo.png)

# Descripción

El juego de mesa más famoso en su versión mejorada ha
llegado a la pantalla. Este innovador Conecta 4 incorpora nuevas características:

- 3 desafiantes modos de juego: modos fácil y difícil contra el ordenador y modo uno contra uno para poder desafiar a tus amigos
- 1 ranking donde poder competir contra tus amigos por la **VICTORIA!**
- Dos idiomas para elegir.
- Animaciones impresionantes que te dejaran con la boca abierta!
- Instrucciones para que hasta el más novato sepa jugar.
- Juega al ritmo de la música con las maravillosas canciones que puedes escuchar a lo largo de la partida.

# Screenshots

La pantalla principal dispone de un sinfín de elementos como los que
se muestran a continuación:

![Menu_ayuda](https://github.com/anderct105/conecta4/blob/feature/readme/src/main/resources/imagenes/menu_ayuda.png)

Durante la partida es posible cambiar la música y encender y apagar los efectos sonoros:

![Tablero_ayuda1](https://github.com/anderct105/conecta4/blob/feature/readme/src/main/resources/imagenes/Tablero_ayuda1.png)

En el modo jugador 1 contra 1 se indica a qué jugador le toca jugar después
![Tablero_ayuda2](https://github.com/anderct105/conecta4/blob/feature/readme/src/main/resources/imagenes/tablero_ayuda2.png)

Tanto en el modo fácil como en el difícil aparecerá un temporizador

# Instalación
## Windows
- conecta4_v1.0.exe
## Linux
- conecta4_v1.0.deb
## Base de datos
- Instalación:
  - <a href='https://dev.mysql.com/downloads/mysql/'>Windows</a>
  - Linux
```
sudo apt-get install mysql-server-5.7
```
```
# Creamos la base de datos
create database conecta4

# Creamos el usuario adminConecta4
create user adminConecta4 identified by ‘adminConecta4’

# Entrar en mySQL como adminConecta4
mysql -u adminConecta4 -p

# Importamos el .sql
mysql -u adminConecta4 -p conecta4 <  
https://github.com/anderct105/conecta4/blob/master/src/main/resources/conecta4.sql
```


