He modificado la plantilla de tipeCalculator para que implemente una estructura viewmodel. Tambien le cambie el tema a uno que me gusta.
La logica para calcular la propina la separe de la interfaz grafica.
Todo lo que tiene que ver con la pantalla de calcular propina se encuentra en la carpeta en UI en el archivo TipCalculatorScreen.kt.
Para que las dos plataformas puedan correr la app, cree un archivo llamado App, el cual ejecuta la app en modo viewmodel, con el tema elegido.