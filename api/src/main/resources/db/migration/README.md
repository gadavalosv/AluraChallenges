usando flyway...
eventualmente puede ocurrir que nos olvidemos de detener el proyecto y se produzca un error
al intentar inicializar la aplicación. En este caso, se mostrará el siguiente error al intentar
inicializar la aplicación:

```sh
Exception encountered during context initialization -
cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException:
Error creating bean with name 'flywayInitializer' defined in class path resource
[org/springframework/boot/autoconfigure/flyway/FlywayAutoConfiguration$FlywayConfiguration.class]:
Validate failed: Migrations have failed validation
```

==>

PARA SOLUCIONAR ESTE PROBLEMA SERÁ NECESARIO ACCEDER A LA BASE DE DATOS DE LA APLICACIÓN Y EJECUTAR
EL SIQUIENTE COMANDO SQL:

```sql
delete from flyway_schema_history where success = 0;
```
o bien si se ejecuto correctamente, pero no como se deseaba obtener los resultados, usar:
```sql
DELETE FROM flyway_schema_history WHERE installed_rank = 3;
--where 3 is the value of the row you mess up
```