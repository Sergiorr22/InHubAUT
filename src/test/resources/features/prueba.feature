@Cotizacion
Feature:Cotización

#  Background es una seria de datos que se repite en todos los escenarios
#  Background:


  @Afiliacion_personaNatural
  Scenario Outline: Cotización

    Given que estoy en el portal Inhub "<producto>" y perfil "<clave>" con "<agente>"
    When Diligencio la informacion del asesor "<clave>"
    And ingreso los datos del formulario datos basicos "<tipoDoc>", "<numDoc>", "<fasecolda>", "<servicio>", "<zona>"
    And Ingreso los datos del formulario datos adicionales
    Then Se selecciona oferta y se guarda cotizacion "<asistencia>", "<email>", "<name>", "<lastName>", "<telefono>"


    Examples:
      | clave | producto    | tipoDoc              | numDoc     | fasecolda | servicio   | zona   | asistencia          | email                        | name   | lastName     | telefono   | agente |
      | 33426 | Automotores | Cedula De Ciudadania | 1028480940 | 01001008  | Particular | Bogota | Asistencia Esencial | ssrodriguezr@axacolpatria.co | Prueba | Automatizada | 3133567812 | 33426  |
      |       | Automotores | Cedula De Ciudadania | 1028480940 | 01001008  | Particular | Bogota | Asistencia Esencial | ssrodriguezr@axacolpatria.co | Prueba | Automatizada | 3133567812 | 33426  |


  @Afiliacion_conplaca
  Scenario Outline: Afiliacion dependiente

    Given que estoy en el portal Inhub "<producto>" y perfil "<clave>" con "<agente>"
    When Diligencio la informacion del asesor "<clave>"
    And ingreso los datos del formulario datos basicos "<tipoDoc>", "<numDoc>", "<fasecolda>", "<placa>", "<servicio>", "<zona>"
    And Ingreso los datos del formulario datos adicionales
    Then Se selecciona oferta y se guarda cotizacion "<asistencia>", "<email>", "<name>", "<lastName>", "<telefono>"

    Examples:
      | clave | producto    | tipoDoc              | numDoc     | fasecolda | servicio   | zona   | asistencia          | email                        | name   | lastName     | telefono   | agente | placa  |
      | 33426 | Automotores | Cedula De Ciudadania | 1028480940 |           | Particular | Bogota | Asistencia Esencial | ssrodriguezr@axacolpatria.co | Prueba | Automatizada | 3133567812 | 33426  | JWP176 |
      |       | Automotores | Cedula De Ciudadania | 1028480940 |           | Particular | Bogota | Asistencia Esencial | ssrodriguezr@axacolpatria.co | Prueba | Automatizada | 3133567812 | 33426  | JWP176 |
