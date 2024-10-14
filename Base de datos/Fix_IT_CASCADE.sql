Create table Taller(
    Codigo_Taller VARCHAR2(10) primary key,
    Nombre_Dueno VARCHAR2(50) NOT NULL,
    Apellido_Dueno VARCHAR2(50) NOT NULL,
    NombreTaller VARCHAR2(50) NOT NULL,
    CorreoElectronico VARCHAR2(50) UNIQUE,
    Contrasena VARCHAR2(250) NOT NULL,
    Telefono VARCHAR2(15) NOT NULL,
    Direccion VARCHAR2(100) NOT NULL
);

Create table Rol(
    UUID_rol VARCHAR2(50) primary key,
    Nombre VARCHAR2(20) NOT NULL
);

Create table Usuario(
    UUID_usuario VARCHAR2(50) PRIMARY KEY,
    UUID_rol VARCHAR2(50) NOT NULL,
    CorreoElectronico VARCHAR2(50) UNIQUE,
    Contrasena VARCHAR2(250) NOT NULL,
    CONSTRAINT fk_rol FOREIGN KEY (UUID_rol) REFERENCES Rol(UUID_rol) ON DELETE CASCADE
);

CREATE TABLE CategoriaItem (
    UUID_item VARCHAR2(50) PRIMARY KEY,
    Nombre VARCHAR2(50) NOT NULL
);

Create table EstadoAsignarOrden(
    UUID_estado Varchar2(50) PRIMARY KEY,
    Nombre VARCHAR2(20) NOT NULL
);

Create table Cliente (
    Dui_cliente VARCHAR2(10) PRIMARY KEY,
    Nombre VARCHAR2(50) NOT NULL,
    Apellido VARCHAR2(50) NOT NULL,
    Contrasena VARCHAR2(250) NOT NULL,
    Correo_Electronico VARCHAR2(50) UNIQUE,
    Telefono VARCHAR2(8) NOT NULL
);

Create table Marca(
    UUID_marca VARCHAR2(50) PRIMARY KEY,
    Nombre VARCHAR2(50) NOT NULL,
    Descripcion VARCHAR2(200) NOT NULL
);

Create table Proveedor(
    Codigo_proveedor VARCHAR2(10) PRIMARY KEY,
    Nombre VARCHAR2(50) NOT NULL,
    Marca VARCHAR2(50) NOT NULL,
    Telefono VARCHAR2(9) NOT NULL,
    Correo_Electronico VARCHAR2(50) UNIQUE,
    Direccion VARCHAR2(200) NOT NULL
);

Create table Servicio(
    UUID_servicio VARCHAR2(50) PRIMARY KEY,
    Nombre VARCHAR2(50) NOT NULL,
    Descripcion VARCHAR2(50) NOT NULL,
    Precio NUMBER(10, 2) CHECK (Precio > 0) NOT NULL
);

Create table ProductoRepuesto(
    UUID_productoRepuesto VARCHAR2(50) PRIMARY KEY,
    UUID_item VARCHAR2(50),
    Nombre VARCHAR2(50) NOT NULL,
    ImagenProductoRepuesto VARCHAR2(255),
    Precio NUMBER(10, 2) CHECK (Precio > 0) NOT NULL,
    CONSTRAINT fk_item FOREIGN KEY (UUID_item) REFERENCES CategoriaItem(UUID_item) ON DELETE CASCADE
);

Create table Modelo(
    UUID_modelo VARCHAR2(50) PRIMARY KEY,
    UUID_marca VARCHAR2(50) NOT NULL,
    Nombre VARCHAR2(20) NOT NULL,
    CONSTRAINT fk_marca FOREIGN KEY (UUID_marca) REFERENCES Marca(UUID_marca) ON DELETE CASCADE
);

Create table Empleado(
    Dui_empleado VARCHAR2(10) PRIMARY KEY,
    UUID_usuario VARCHAR2(50) NOT NULL UNIQUE,
    Nombre VARCHAR2(100) NOT NULL,
    Apellido VARCHAR2(100) NOT NULL,
    ImagenEmpleado VARCHAR2(255),
    FechaNacimiento VARCHAR(200) NOT NULL,
    Telefono VARCHAR2(9) NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (UUID_usuario) REFERENCES Usuario(UUID_usuario) ON DELETE CASCADE
);

Create table Carro(
    Placa_carro VARCHAR2(8) PRIMARY KEY,
    Dui_cliente VARCHAR2(10) NOT NULL,
    UUID_modelo VARCHAR2(50) NOT NULL,
    Color VARCHAR2(15) NOT NULL,
    Ano Varchar2(20) NOT NULL,
    ImagenCarro VARCHAR2(255),
    FechaRegistro VARCHAR2(200) NOT NULL,
    Descripcion VARCHAR2(200) NOT NULL,
    CONSTRAINT fk_cliente FOREIGN KEY (Dui_cliente) REFERENCES Cliente(Dui_cliente) ON DELETE CASCADE,
    CONSTRAINT fk_modelo FOREIGN KEY (UUID_modelo) REFERENCES Modelo(UUID_modelo) ON DELETE CASCADE
);

Create table HistorialCarro(
    UUID_historialCarro Varchar2(50) PRIMARY KEY,
    Placa_carro VARCHAR2(8) NOT NULL,
    Descripcion VARCHAR2(150) NOT NULL,
    CONSTRAINT fk_carro FOREIGN KEY (Placa_carro) REFERENCES Carro(Placa_carro) ON DELETE CASCADE
);

Create table Cita (
    UUID_cita Varchar2(50) PRIMARY KEY,
    Dui_cliente VARCHAR2(10) NOT NULL,
    Dui_empleado VARCHAR2(10) NOT NULL,
    Fecha_cita VARCHAR2(200) NOT NULL,
    Hora_cita VARCHAR2(200) NOT NULL,
    Descripcion VARCHAR2(250) NOT NULL,
    CONSTRAINT fk_c_empleado FOREIGN KEY (Dui_empleado) REFERENCES Empleado(Dui_empleado) ON DELETE CASCADE,
    CONSTRAINT fk_c_cliente FOREIGN KEY (Dui_cliente) REFERENCES Cliente(Dui_cliente) ON DELETE CASCADE
);

Create table AsignarOrden(
    UUID_AsignarOrden Varchar2(50) PRIMARY KEY,
    UUID_Cita Varchar2(50) NOT NULL,
    UUID_servicio Varchar2(50) NOT NULL,
    UUID_estado Varchar2(50) NOT NULL,
    Carro_Empleado Varchar2(50) NOT NULL,
    FechaAsignacion VARCHAR2(200) NOT NULL,
    FechaFinalizacion VARCHAR2(200) NOT NULL,
    Descripcion VARCHAR2(150) NOT NULL,
    CONSTRAINT fk_cita_tarea FOREIGN KEY (UUID_Cita) REFERENCES Cita(UUID_Cita) ON DELETE CASCADE,
    CONSTRAINT fk_servicio_tarea FOREIGN KEY (UUID_servicio) REFERENCES Servicio(UUID_servicio) ON DELETE CASCADE,
    CONSTRAINT fk_estado FOREIGN KEY (UUID_estado) REFERENCES EstadoAsignarOrden(UUID_estado) ON DELETE CASCADE
);

Create table Factura(
    UUID_factura VARCHAR2(50) PRIMARY KEY,
    FacturaIdentificacion Varchar2(50) NOT NULL,
    FechaEmision VARCHAR2(200) NOT NULL,
    FechaVencimiento VARCHAR2(200) NOT NULL
);


Create table DetalleFactura(
    UUID_DetalleFactura VARCHAR2(50) PRIMARY KEY,
    UUID_factura VARCHAR2(50) NOT NULL,
    UUID_productoRepuesto VARCHAR2(50) NOT NULL,
    UUID_AsignarOrden VARCHAR2(50) NOT NULL,
    CONSTRAINT fk_detalle_factura FOREIGN KEY (UUID_factura) REFERENCES Factura(UUID_factura) ON DELETE CASCADE,
    CONSTRAINT fk_detalle_item FOREIGN KEY (UUID_productoRepuesto) REFERENCES ProductoRepuesto(UUID_productoRepuesto) ON DELETE CASCADE,
    CONSTRAINT fk_detalle_tarea FOREIGN KEY (UUID_AsignarOrden) REFERENCES AsignarOrden(UUID_AsignarOrden) ON DELETE CASCADE
);

Create table DetalleProveedor(
    UUID_DetalleProveedor VARCHAR2(50) NOT NULL,
    UUID_productoRepuesto VARCHAR2(50) NOT NULL,
    Codigo_proveedor VARCHAR2(10) NOT NULL,
    Cantidad NUMBER(10, 2) NOT NULL CHECK (Cantidad > 0),
    FechaSuministro VARCHAR2(200) NOT NULL,
    CONSTRAINT fk_producto_proveedor FOREIGN KEY (UUID_productoRepuesto) REFERENCES ProductoRepuesto(UUID_productoRepuesto) ON DELETE CASCADE,
    CONSTRAINT fk_proveedor_producto FOREIGN KEY (Codigo_proveedor) REFERENCES Proveedor(Codigo_proveedor) ON DELETE CASCADE
);

Create table Auditoria(
    IdAuditoria int primary key not null,
    Tabla Varchar2(50) not null,
    Operacion Varchar2(50) not null,
    Usuario Varchar2(50) not null,
    Fecha Date not null,
    Pk_referencia Varchar2(200) not null,
    Detalles_cambios Varchar2(500) not null
);




--------------------------------------------------------------------------------------------------------------
--SENCUENCIA Y TRIGGER  PARA QUE EN LA TABLA DE FACTURA SE INSERTE AUTOMATICAMENTE LA FECHA DE HOY Y EL UUID

CREATE SEQUENCE SEQfactura
START WITH 1
INCREMENT BY 1;

Create or replace trigger TrigFactura
BEFORE INSERT ON Factura
FOR EACH ROW
BEGIN
    :NEW.UUID_factura := 'Factura-' || SYSDATE || '-' || LPAD(SEQfactura.NEXTVAL, 4, '0');
    :NEW.FechaEmision := SYSDATE;
END;

insert into factura (fechavencimiento) values ('26/08/2024');
insert into factura (fechaemision, fechavencimiento) values (sysdate,'25/08/2024');

select * from factura;

--------------------------------------------------------------------------------------------------------------


CREATE SEQUENCE SEQproveedor
START WITH 1001
INCREMENT BY 1;


CREATE OR REPLACE TRIGGER trg_proveedor
BEFORE INSERT ON Proveedor
FOR EACH ROW
BEGIN
    -- Si la columna Codigo_proveedor es nula, se genera el valor usando la secuencia SEQproveedor
    IF :NEW.Codigo_Proveedor IS NULL THEN
        :NEW.Codigo_proveedor := SEQproveedor.NEXTVAL;
    END IF;
END;

--------------------------------------------------------------------------------------------------------------


//PROCEDIMIENTO EL CUAL ACTUALIZA CUALQUIER CAMPO DE LA TABLA DE CLIENTE 

CREATE OR REPLACE PROCEDURE actualizar_cliente (
p_dui_cliente IN Cliente.Dui_cliente%TYPE,
p_nombre IN Cliente.Nombre%TYPE,
p_apellido IN Cliente.Apellido%TYPE,
p_contrasena IN Cliente.Contrasena%TYPE,
p_correo IN Cliente.Correo_Electronico%TYPE,
p_telefono IN Cliente.Telefono%TYPE
)
AS
BEGIN
UPDATE Cliente
SET Nombre = p_nombre,
Apellido = p_apellido,
Contrasena = p_contrasena,
Correo_Electronico = p_correo,
Telefono = p_telefono
WHERE Dui_cliente = p_dui_cliente;
END;


/*BEGIN
UPDATE Cliente
SET Nombre = 'El acabado', Apellido = 'boeeee'
WHERE Dui_cliente = '345723699';
END;  */


--------------------------------------------------------------------------------------------------------------
//SECUENCIA PARA LA TABLA AUDITORIA QUE SEA AUTOICREMENTABLE

CREATE SEQUENCE seqAuditoria
START WITH 1
INCREMENT BY 1;

--Trigger Usuario
CREATE OR REPLACE TRIGGER trg_auditoria_usuario
AFTER INSERT OR DELETE ON Usuario
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO Auditoria(
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'Usuario',
            'Agregar Usuario',
            USER,
            SYSDATE,
            :NEW.UUID_Usuario,
            'Correo=' || :NEW.CorreoElectronico
        );
        
    ELSIF DELETING THEN
        INSERT INTO Auditoria (
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'Usuario',
            'Eliminar Usuario',
            USER,
            SYSDATE,
            :OLD.UUID_usuario,
            'Correo=' || :OLD.CorreoElectronico
        );
    END IF;
END;


--Trigger Cliente
CREATE OR REPLACE TRIGGER trg_auditoria_cliente
AFTER INSERT OR DELETE ON Cliente
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO Auditoria(
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'Cliente',
            'Agregar Cliente',
            USER,
            SYSDATE,
            :NEW.Dui_cliente,
            'Nombre=' || :NEW.Nombre ||
            ', Apellido=' || :NEW.Apellido
        );
        
    ELSIF DELETING THEN
        INSERT INTO Auditoria (
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'Cliente',
            'Eliminar Cliente',
            USER,
            SYSDATE,
            :OLD.Dui_cliente,
            'Nombre=' || :OLD.Nombre ||
            ', Apellido=' || :OLD.Apellido
        );
    END IF;
END;


--Trigger Marca
CREATE OR REPLACE TRIGGER trg_auditoria_marca
AFTER INSERT OR DELETE ON Marca
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO Auditoria(
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'Marca',
            'Agregar Marca',
            USER,
            SYSDATE,
            :NEW.UUID_marca,
            'Nombre=' || :NEW.Nombre
        );
        
    ELSIF DELETING THEN
        INSERT INTO Auditoria (
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'Marca',
            'Eliminar Marca',
            USER,
            SYSDATE,
            :OLD.UUID_marca,
            'Nombre=' || :OLD.Nombre
        );
    END IF;
END;


--Trigger proveedor
CREATE OR REPLACE TRIGGER trg_auditoria_proveedor
AFTER INSERT OR DELETE ON Proveedor
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO Auditoria(
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'Proveedor',
            'Agregar Proveedor',
            USER,
            SYSDATE,
            :NEW.Codigo_proveedor,
            'Nombre=' || :NEW.Nombre ||
            ', Marca=' || :NEW.Marca
        );
        
    ELSIF DELETING THEN
        INSERT INTO Auditoria (
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'Proveedor',
            'Eliminar Proveedor',
            USER,
            SYSDATE,
            :OLD.Codigo_proveedor,
            'Nombre=' || :OLD.Nombre ||
            ', Marca=' || :OLD.Marca
        );
    END IF;
END;


--Trigger servicio
CREATE OR REPLACE TRIGGER trg_auditoria_servicio
AFTER INSERT OR DELETE ON Servicio
FOR EACH ROW
BEGIN
        IF INSERTING THEN
            INSERT INTO Auditoria(
                IdAuditoria, 
                Tabla, 
                Operacion, 
                Usuario, 
                Fecha, 
                Pk_referencia, 
                Detalles_cambios
            ) VALUES (
                seqAuditoria.NEXTVAL,
                'Servicio',
                'Agregar Servicio',
                USER,
                SYSDATE,
                :NEW.UUID_servicio,
                'Nombre=' || :NEW.Nombre
            );
            
        ELSIF DELETING THEN
            INSERT INTO Auditoria (
                IdAuditoria, 
                Tabla, 
                Operacion, 
                Usuario, 
                Fecha, 
                Pk_referencia, 
                Detalles_cambios
            ) VALUES (
                seqAuditoria.NEXTVAL,
                'Servicio',
                'Eliminar Servicio',
                USER,
                SYSDATE,
                :OLD.UUID_servicio,
                'Nombre=' || :OLD.Nombre
            );
        END IF;
END;


--Trigger ProductoRepuesto
CREATE OR REPLACE TRIGGER trg_auditoria_productoRepuesto
AFTER INSERT OR DELETE ON ProductoRepuesto
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO Auditoria(
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'ProductoRepuesto',
            'Agregar Producto o Repuesto',
            USER,
            SYSDATE,
            :NEW.UUID_productoRepuesto,
            'Producto o repuesto=' || :NEW.Nombre
        );
        
    ELSIF DELETING THEN
        INSERT INTO Auditoria (
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'ProductoRepuesto',
            'Eliminar Producto o repuesto',
            USER,
            SYSDATE,
            :OLD.UUID_productoRepuesto,
            'Producto o repuesto=' || :OLD.Nombre
        );
    END IF;
END;


--Trigger modelo
CREATE OR REPLACE TRIGGER trg_auditoria_modelo
AFTER INSERT OR DELETE ON Modelo
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO Auditoria(
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'Modelo',
            'Agregar Modelo',
            USER,
            SYSDATE,
            :NEW.UUID_modelo,
            'Modelo=' || :NEW.Nombre
        );
        
    ELSIF DELETING THEN
        INSERT INTO Auditoria (
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'Modelo',
            'Eliminar Modelo',
            USER,
            SYSDATE,
            :OLD.UUID_modelo,
            'Modelo=' || :OLD.Nombre
        );
    END IF;
END;


--Trigger Empleado
CREATE OR REPLACE TRIGGER trg_auditoria_empleado
AFTER INSERT OR DELETE ON Empleado
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO Auditoria(
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'Empleado',
            'Agregar Empleado',
            USER,
            SYSDATE,
            :NEW.Dui_empleado,
            'Nombre=' || :NEW.Nombre ||
            ', Apellido=' || :NEW.Apellido
        );
        
    ELSIF DELETING THEN
        INSERT INTO Auditoria (
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'Empleado',
            'Eliminar Empleado',
            USER,
            SYSDATE,
            :OLD.Dui_empleado,
            'Nombre=' || :OLD.Nombre ||
            ', Apellido=' || :OLD.Apellido
        );
    END IF;
END;


--Trigger Carro
CREATE OR REPLACE TRIGGER trg_auditoria_carro
AFTER INSERT OR DELETE ON Carro
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO Auditoria(
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'Carro',
            'Agregar Carro',
            USER,
            SYSDATE,
            :NEW.Placa_carro,
            'Color=' || :NEW.Color ||
            ', Anio=' || :NEW.Ano
        );
        
    ELSIF DELETING THEN
        INSERT INTO Auditoria (
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'Carro',
            'Eliminar Carro',
            USER,
            SYSDATE,
            :OLD.Placa_carro,
            'Color=' || :OLD.Color ||
            ', Anio=' || :OLD.Ano
        );
    END IF;
END;


--Trigger Historial Carro
CREATE OR REPLACE TRIGGER trg_auditoria_historialCarro
AFTER INSERT OR DELETE ON HistorialCarro
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO Auditoria(
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'HistorialCarro',
            'Agregar Historial a carro',
            USER,
            SYSDATE,
            :NEW.UUID_historialCarro,
            'Placa de carro=' || :NEW.Placa_carro ||
            ', Descripcion=' || :NEW.Descripcion
        );
        
    ELSIF DELETING THEN
        INSERT INTO Auditoria (
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'HistorialCarro',
            'Eliminar historial a carro',
            USER,
            SYSDATE,
            :OLD.UUID_historialCarro,
            'Placa de carro=' || :OLD.Placa_carro ||
            ', Descripcion=' || :OLD.Descripcion
        );
    END IF;
END;


--Trigger para Cita
CREATE OR REPLACE TRIGGER trg_auditoria_cita
AFTER INSERT OR DELETE ON Cita
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO Auditoria(
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'Cita',
            'Agregar Cita',
            USER,
            SYSDATE,
            :NEW.UUID_cita,
            'Fecha de la cita=' || :NEW.Fecha_cita
        );
        
    ELSIF DELETING THEN
        INSERT INTO Auditoria (
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'Cita',
            'Eliminar cita',
            USER,
            SYSDATE,
            :OLD.UUID_cita,
            'Fecha de la cita=' || :OLD.Fecha_cita
        );
    END IF;
END;


--Trigger para AsignarOrden
CREATE OR REPLACE TRIGGER trg_auditoria_asignarOrden
AFTER INSERT OR DELETE ON AsignarOrden
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO Auditoria(
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'AsignarOrden',
            'Agregar Asignacion de tarea',
            USER,
            SYSDATE,
            :NEW.UUID_AsignarOrden,
            'Fecha de asignacion=' || :NEW.FechaAsignacion ||
            ', Fecha de finalizacion=' || :NEW.FechaFinalizacion
        );
        
    ELSIF DELETING THEN
        INSERT INTO Auditoria (
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'AsignarOrden',
            'Eliminar Asignacion de tarea',
            USER,
            SYSDATE,
            :OLD.UUID_AsignarOrden,
            'Fecha de asignacion=' || :OLD.FechaAsignacion ||
            ', Fecha de finalizacion=' || :OLD.FechaFinalizacion
        );
    END IF;
END;


--Trigger para Factura
CREATE OR REPLACE TRIGGER trg_auditoria_factura
AFTER INSERT OR DELETE ON Factura
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO Auditoria(
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'Factura',
            'Agregar Factura',
            USER,
            SYSDATE,
            :NEW.UUID_factura,
            'Emision de factura=' || :NEW.FechaEmision ||
            ', Vencimiento de factura=' || :NEW.FechaVencimiento
        );
        
    ELSIF DELETING THEN
        INSERT INTO Auditoria (
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'AsignarOrden',
            'Eliminar Asignacion de tarea',
            USER,
            SYSDATE,
            :OLD.UUID_factura,
            'Emision de factura=' || :OLD.FechaEmision ||
            ', Vencimiento de factura=' || :OLD.FechaVencimiento
        );
    END IF;
END;


--Trigger DetalleFactura
CREATE OR REPLACE TRIGGER trg_auditoria_detalleFactura
AFTER INSERT OR DELETE ON DetalleFactura
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO Auditoria(
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'DetalleFactura',
            'Agregar DetalleFactura',
            USER,
            SYSDATE,
            :NEW.UUID_DetalleFactura,
            NULL
        );
        
    ELSIF DELETING THEN
        INSERT INTO Auditoria (
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'DetalleFactura',
            'Eliminar DetalleFactura',
            USER,
            SYSDATE,
            :OLD.UUID_DetalleFactura,
            NULL
        );
    END IF;
END;


--Trigger detalleProveedor
CREATE OR REPLACE TRIGGER trg_auditoria_detalleProveedor
AFTER INSERT OR DELETE ON DetalleProveedor
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO Auditoria(
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'DetalleProveedor',
            'Agregar DetalleProveedor',
            USER,
            SYSDATE,
            :NEW.UUID_DetalleProveedor,
            'Codigo del proveedor=' || :NEW.Codigo_proveedor
        );
        
    ELSIF DELETING THEN
        INSERT INTO Auditoria (
            IdAuditoria, 
            Tabla, 
            Operacion, 
            Usuario, 
            Fecha, 
            Pk_referencia, 
            Detalles_cambios
        ) VALUES (
            seqAuditoria.NEXTVAL,
            'DetalleProveedor',
            'Eliminar DetalleProveedor',
            USER,
            SYSDATE,
            :OLD.UUID_DetalleProveedor,
            'Codigo del proveedor=' || :OLD.Codigo_proveedor
        );
    END IF;
END;


select * from empleado;
delete from empleado where Dui_empleado = '';
select * from Auditoria;
--------------------------------------------------------------------------------------------------------------


INSERT INTO Taller (Codigo_Taller, Nombre_Dueno, Apellido_Dueno, NombreTaller, CorreoElectronico, Contrasena, Telefono, Direccion)
VALUES ('TallerDisp', 'Wasi', 'Barahona', 'fernando.merino@gmail.com', 'Los bellakos', 'merino12', '23473411', 'Mexicanos Calle Sur');


INSERT ALL
 INTO Rol (UUID_rol, Nombre) VALUES (SYS_GUID(), 'Administrador')
 INTO Rol (UUID_rol, Nombre) VALUES (SYS_GUID(), 'Empleado')
SELECT * FROM dual;


INSERT ALL
 INTO Usuario (UUID_usuario, UUID_rol, CorreoElectronico, Contrasena) 
 VALUES (SYS_GUID(), (SELECT UUID_rol FROM Rol WHERE Nombre = 'Administrador'), 'mario.garcia@gmail.com', 'mario123')
 INTO Usuario (UUID_usuario, UUID_rol, CorreoElectronico, Contrasena)
 VALUES (SYS_GUID(), (SELECT UUID_rol FROM Rol WHERE Nombre = 'Empleado'), 'rebeca.zelaya@gmail.com', 'rebe1234')
 INTO Usuario (UUID_usuario, UUID_rol, CorreoElectronico, Contrasena) 
 VALUES (SYS_GUID(), (SELECT UUID_rol FROM Rol WHERE Nombre = 'Empleado'), 'fatima.juarez@gmail.com', 'fati1234')
 INTO Usuario (UUID_usuario, UUID_rol, CorreoElectronico, Contrasena) 
 VALUES (SYS_GUID(), (SELECT UUID_rol FROM Rol WHERE Nombre = 'Empleado'), 'juan.lopez@gmail.com', 'juan1234')
 INTO Usuario (UUID_usuario, UUID_rol, CorreoElectronico, Contrasena) 
 VALUES (SYS_GUID(), (SELECT UUID_rol FROM Rol WHERE Nombre = 'Empleado'), 'maria.dominguez@gmail.com', 'maria123')
SELECT * FROM dual;

INSERT INTO CategoriaItem (UUID_item, Nombre) VALUES (SYS_GUID(), 'Producto');
INSERT INTO CategoriaItem (UUID_item, Nombre) VALUES (SYS_GUID(), 'Repuesto');


INSERT ALL
 INTO EstadoAsignarOrden (UUID_estado, Nombre)
 VALUES (SYS_GUID(), 'Sin comenzar')
 INTO EstadoAsignarOrden (UUID_estado, Nombre) 
 VALUES (SYS_GUID(), 'En proceso')
 INTO EstadoAsignarOrden (UUID_estado, Nombre) 
 VALUES (SYS_GUID(), 'Terminado')
SELECT * FROM dual;


INSERT ALL
 INTO Cliente (Dui_cliente, Nombre, Apellido, Contrasena, Correo_Electronico, Telefono)
 VALUES ('345723699', 'Carlos', 'Goncho', 'goncho123', 'goncho@gmail.com', '34598115')
 INTO Cliente (Dui_cliente, Nombre, Apellido, Contrasena, Correo_Electronico, Telefono)
 VALUES ('091345821', 'Lautaro', 'Martin', 'lauti123', 'martin@gmail.com', '12056923')
 INTO Cliente (Dui_cliente, Nombre, Apellido, Contrasena, Correo_Electronico, Telefono)
 VALUES ('456789012', 'Lionel', 'Messi', 'messi123', 'messi@gmail.com', '10493682')
 INTO Cliente (Dui_cliente, Nombre, Apellido, Contrasena, Correo_Electronico, Telefono)
 VALUES ('218957987', 'Paulo', 'Dybala', 'dybala123', 'dybala@gmail.com', '23489679')
 INTO Cliente (Dui_cliente, Nombre, Apellido, Contrasena, Correo_Electronico, Telefono)
 VALUES ('092438653', 'Luis', 'De la Fuente', 'fuente123', 'luis@gmail.com', '93082462')
SELECT * FROM dual;


INSERT ALL
 INTO Marca (UUID_marca, Nombre, Descripcion) 
 VALUES (SYS_GUID(), 'Toyota', 'Marca japonesa conocida por su calidad en carros')
 INTO Marca (UUID_marca, Nombre, Descripcion) 
 VALUES (SYS_GUID(), 'Honda', 'Marca japonesa destacada por sus motores de buena calidad.')
 INTO Marca (UUID_marca, Nombre, Descripcion) 
 VALUES (SYS_GUID(), 'Ford', 'Marca americana reconocida por sus camionetas robustas')
 INTO Marca (UUID_marca, Nombre, Descripcion) 
 VALUES (SYS_GUID(), 'Chevrolet', 'Marca americana que ofrece una amplia variedad de veh culos.')
 INTO Marca (UUID_marca, Nombre, Descripcion) 
 VALUES (SYS_GUID(), 'Volkswagen', 'Marca alemana conocida por su ingenier a y modelos como el Golf y el Beetle.')
SELECT * FROM dual;


INSERT ALL
    INTO Proveedor (Nombre, Marca, Telefono, Correo_Electronico, Direccion) 
    VALUES ('Carlos', 'Alcaraz', '12835689', 'carlos.alcaraz@gmail.com', 'Bulevar Los Proceres')
    INTO Proveedor (Nombre, Marca, Telefono, Correo_Electronico, Direccion) 
    VALUES ('Laura', 'Bonilla', '01923578', 'laura.bonilla@gmail.com', 'Caba as avenida sur')
    INTO Proveedor (Nombre, Marca, Telefono, Correo_Electronico, Direccion) 
    VALUES ('Josefin', 'Martinez', '12938056', 'josefin.martinez@gmail.com', 'La Libertad avenida norte')
    INTO Proveedor (Nombre, Marca, Telefono, Correo_Electronico, Direccion) 
    VALUES ('David', 'Lopez', '24572313', 'david.lopez@gmail.com', 'Calle San francisco')
    INTO Proveedor (Nombre, Marca, Telefono, Correo_Electronico, Direccion) 
    VALUES ('Luis', 'Enrique', '23388572', 'luis.enrique@gmail.com', 'Las Arboledas')
SELECT * FROM dual;



INSERT ALL
    INTO Servicio (UUID_servicio, Nombre, Descripcion, Precio) 
    VALUES (SYS_GUID(), 'Cambio de Aceite', 'Cambio de aceite de motor', 25.99)
    INTO Servicio (UUID_servicio, Nombre, Descripcion, Precio) 
    VALUES (SYS_GUID(), 'Alineacion y Balanceo', 'Alineacion y balanceo de ruedas', 47.99)
    INTO Servicio (UUID_servicio, Nombre, Descripcion, Precio) 
    VALUES (SYS_GUID(), 'Revision de Frenos', 'Inspeccion y ajuste de frenos', 29.99)
    INTO Servicio (UUID_servicio, Nombre, Descripcion, Precio) 
    VALUES (SYS_GUID(), 'Cambio de Bateria', 'Reemplazo de bateria del vehiculo', 79.99)
    INTO Servicio (UUID_servicio, Nombre, Descripcion, Precio) 
    VALUES (SYS_GUID(), 'Diagnostico Completo', 'Diagnostico completo del vehiculo', 109.99)
SELECT * FROM dual;


INSERT ALL
 INTO ProductoRepuesto (UUID_productoRepuesto, UUID_item, Nombre, ImagenProductoRepuesto, Precio) 
 VALUES (SYS_GUID(), (SELECT UUID_item FROM CategoriaItem WHERE Nombre = 'Producto'), 'Aceite de motor', 'img1', 6.99)
 INTO ProductoRepuesto (UUID_productoRepuesto, UUID_item, Nombre, ImagenProductoRepuesto, Precio) 
 VALUES (SYS_GUID(), (SELECT UUID_item FROM CategoriaItem WHERE Nombre = 'Repuesto'), 'Filtro de aire', 'img2', 10.99)
 INTO ProductoRepuesto (UUID_productoRepuesto, UUID_item, Nombre, ImagenProductoRepuesto, Precio) 
 VALUES (SYS_GUID(), (SELECT UUID_item FROM CategoriaItem WHERE Nombre = 'Repuesto'), 'Bateria de coche', 'img3', 70.99)
 INTO ProductoRepuesto (UUID_productoRepuesto, UUID_item, Nombre, ImagenProductoRepuesto, Precio) 
 VALUES (SYS_GUID(), (SELECT UUID_item FROM CategoriaItem WHERE Nombre = 'Repuesto'), 'Pastillas de freno', 'img4', 20.99)
 INTO ProductoRepuesto (UUID_productoRepuesto, UUID_item, Nombre, ImagenProductoRepuesto, Precio) 
 VALUES (SYS_GUID(), (SELECT UUID_item FROM CategoriaItem WHERE Nombre = 'Repuesto'), 'Neumatico', 'img5', 50.99)
SELECT * FROM dual;


INSERT ALL
 INTO Modelo (UUID_modelo, UUID_marca, Nombre) 
 VALUES (SYS_GUID(), (SELECT UUID_marca FROM Marca WHERE Nombre = 'Toyota'), 'Sedan')
 INTO Modelo (UUID_modelo, UUID_marca, Nombre) 
 VALUES (SYS_GUID(), (SELECT UUID_marca FROM Marca WHERE Nombre = 'Honda'), 'CR-V Hybrid')
 INTO Modelo (UUID_modelo, UUID_marca, Nombre) 
 VALUES (SYS_GUID(), (SELECT UUID_marca FROM Marca WHERE Nombre = 'Ford'), 'EDGE ST')
 INTO Modelo (UUID_modelo, UUID_marca, Nombre) 
 VALUES (SYS_GUID(), (SELECT UUID_marca FROM Marca WHERE Nombre = 'Chevrolet'), 'Montana')
 INTO Modelo (UUID_modelo, UUID_marca, Nombre) 
 VALUES (SYS_GUID(), (SELECT UUID_marca FROM Marca WHERE Nombre = 'Volkswagen'), 'Nuevo Taigun')
SELECT * FROM dual;


INSERT ALL
 INTO Empleado (Dui_empleado, UUID_usuario, Nombre, Apellido, ImagenEmpleado, FechaNacimiento, Telefono)
 VALUES ('438623602', (SELECT UUID_usuario FROM Usuario WHERE CorreoElectronico = 'mario.garcia@gmail.com'), 'Mario', 'Garcia', 'img1', '2005-05-15', '83462396')
 INTO Empleado (Dui_empleado, UUID_usuario, Nombre, Apellido, ImagenEmpleado, FechaNacimiento, Telefono)
 VALUES ('234734141', (SELECT UUID_usuario FROM Usuario WHERE CorreoElectronico = 'rebeca.zelaya@gmail.com'), 'Rebeca', 'Zelaya', 'img2', '1985-08-20', '28976122')
 INTO Empleado (Dui_empleado, UUID_usuario, Nombre, Apellido, ImagenEmpleado, FechaNacimiento, Telefono)
 VALUES ('527223462', (SELECT UUID_usuario FROM Usuario WHERE CorreoElectronico = 'fatima.juarez@gmail.com'), 'Fatima', 'Juarez', 'img3', '1992-03-10', '09348267')
 INTO Empleado (Dui_empleado, UUID_usuario, Nombre, Apellido, ImagenEmpleado, FechaNacimiento, Telefono)
 VALUES ('345834343', (SELECT UUID_usuario FROM Usuario WHERE CorreoElectronico = 'juan.lopez@gmail.com'), 'Juan', 'Lopez', 'img4', '1988-11-25', '90283167')
 INTO Empleado (Dui_empleado, UUID_usuario, Nombre, Apellido, ImagenEmpleado, FechaNacimiento, Telefono)
 VALUES ('534233464', (SELECT UUID_usuario FROM Usuario WHERE CorreoElectronico = 'maria.dominguez@gmail.com'), 'Maria', 'Dominguez', 'img5', '1995-07-03', '29086126')
SELECT * FROM dual;


INSERT ALL
 INTO Carro (Placa_carro, Dui_cliente, UUID_modelo, Color, Ano, ImagenCarro, FechaRegistro, Descripcion)
 VALUES ('ABC1234', '345723699', (SELECT UUID_modelo FROM Modelo WHERE Nombre = 'Sedan'), 'Gris', '2020', 'img1', '29/06/2024', 'Carro deportivo')
 INTO Carro (Placa_carro, Dui_cliente, UUID_modelo, Color, Ano, ImagenCarro, FechaRegistro, Descripcion)
 VALUES ('XYZ5678', '091345821', (SELECT UUID_modelo FROM Modelo WHERE Nombre = 'CR-V Hybrid'), 'Negro', '2019', 'img2', '29/06/2024', 'Carro familiar')
 INTO Carro (Placa_carro, Dui_cliente, UUID_modelo, Color, Ano, ImagenCarro, FechaRegistro, Descripcion)
 VALUES ('DEF9012', '456789012', (SELECT UUID_modelo FROM Modelo WHERE Nombre = 'EDGE ST'), 'Blanco', '2021', 'img3', '29/06/2024', 'Carro urbano')
 INTO Carro (Placa_carro, Dui_cliente, UUID_modelo, Color, Ano, ImagenCarro, FechaRegistro, Descripcion)
 VALUES ('GHI3456', '218957987', (SELECT UUID_modelo FROM Modelo WHERE Nombre = 'Montana'), 'Azul', '2017', 'img4', '29/06/2024', 'Carro SUV')
 INTO Carro (Placa_carro, Dui_cliente, UUID_modelo, Color, Ano, ImagenCarro, FechaRegistro, Descripcion)
 VALUES ('JKL7890', '092438653', (SELECT UUID_modelo FROM Modelo WHERE Nombre = 'Nuevo Taigun'), 'Rojo', '2020', 'img5', '29/06/2024', 'Carro elÃ©ctrico')
SELECT * FROM dual;



INSERT ALL
  INTO HistorialCarro (UUID_historialCarro, Placa_carro, Descripcion)
  VALUES (SYS_GUID(), 'ABC1234', 'Revision de motor')
  INTO HistorialCarro (UUID_historialCarro, Placa_carro, Descripcion)
  VALUES (SYS_GUID(), 'XYZ5678', 'Cambio de aceite')
  INTO HistorialCarro (UUID_historialCarro, Placa_carro, Descripcion)
  VALUES (SYS_GUID(), 'DEF9012', 'Alineacion y balanceo')
  INTO HistorialCarro (UUID_historialCarro, Placa_carro, Descripcion)
  VALUES (SYS_GUID(), 'GHI3456', 'Reparacion de frenos')
  INTO HistorialCarro (UUID_historialCarro, Placa_carro, Descripcion)
  VALUES (SYS_GUID(), 'JKL7890', 'Cambio de llantas')
SELECT * FROM dual;

select * from cliente;

INSERT ALL
  INTO Cita (UUID_cita, Dui_cliente, Dui_empleado, Fecha_cita, Hora_cita, Descripcion)
  VALUES (SYS_GUID(), '345723699', '438623602', '2024-08-01', '09:00', 'Revision general del vehiculo')
  INTO Cita (UUID_cita, Dui_cliente, Dui_empleado, Fecha_cita, Hora_cita, Descripcion)
  VALUES (SYS_GUID(), '091345821', '234734141', '2024-08-02', '11:30', 'Cambio de aceite y filtro')
  INTO Cita (UUID_cita, Dui_cliente, Dui_empleado, Fecha_cita, Hora_cita, Descripcion)
  VALUES (SYS_GUID(), '456789012', '527223462', '2024-08-03', '14:00', 'Alineacion y balanceo de llantas')
  INTO Cita (UUID_cita, Dui_cliente, Dui_empleado, Fecha_cita, Hora_cita, Descripcion)
  VALUES (SYS_GUID(), '218957987', '345834343', '2024-08-04', '10:00', 'Inspeccion de frenos y suspension')
  INTO Cita (UUID_cita, Dui_cliente, Dui_empleado, Fecha_cita, Hora_cita, Descripcion)
  VALUES (SYS_GUID(), '092438653', '534233464', '2024-08-05', '16:00', 'Reparacion de sistema electrico')
SELECT * FROM dual;



INSERT INTO AsignarOrden(UUID_AsignarOrden, UUID_cita, UUID_servicio, UUID_estado, Carro_Empleado, FechaAsignacion, FechaFinalizacion, Descripcion)
  VALUES (SYS_GUID(), 
          (SELECT UUID_cita FROM Cita WHERE Descripcion = 'Revision general del vehículo'), 
          (SELECT UUID_servicio FROM Servicio WHERE Nombre = 'Diagnostico Completo'), 
          (SELECT UUID_estado FROM EstadoAsignarOrden WHERE Nombre = 'En proceso'),   'Carro de Carlos',
          '25/08/2024', '30/08/2024', 'Orden de mantenimiento preventivo');

INSERT INTO AsignarOrden (UUID_AsignarOrden, UUID_cita, UUID_servicio, UUID_estado,Carro_Empleado, FechaAsignacion, FechaFinalizacion, Descripcion)
  VALUES (SYS_GUID(), 
          (SELECT UUID_cita FROM Cita WHERE Descripcion = 'Cambio de aceite y filtro'), 
          (SELECT UUID_servicio FROM Servicio WHERE Nombre = 'Cambio de Aceite'), 
          (SELECT UUID_estado FROM EstadoAsignarOrden WHERE Nombre = 'En proceso'),  'Carro de Lautaro',
          '25/08/2024', '30/08/2024', 'Cambio de aceite y frenos');


INSERT INTO AsignarOrden (UUID_AsignarOrden, UUID_cita, UUID_servicio, UUID_estado,Carro_Empleado, FechaAsignacion, FechaFinalizacion, Descripcion)
  VALUES (SYS_GUID(), 
          (SELECT UUID_cita FROM Cita WHERE Descripcion = 'Alineacion y balanceo de llantas'), 
          (SELECT UUID_servicio FROM Servicio WHERE Nombre = 'Alineacion y Balanceo'), 
          (SELECT UUID_estado FROM EstadoAsignarOrden WHERE Nombre = 'Sin comenzar'),'Carro de Lionel',
          '25/08/2024', '30/08/2024', 'Alineación y balanceo carro luis');

INSERT INTO AsignarOrden (UUID_AsignarOrden, UUID_cita, UUID_servicio, UUID_estado, Carro_Empleado, FechaAsignacion, FechaFinalizacion, Descripcion)
VALUES (
    SYS_GUID(), 
    (SELECT UUID_cita FROM Cita WHERE Descripcion = 'Inspeccion de frenos y suspensión'), 
    (SELECT UUID_servicio FROM Servicio WHERE Nombre = 'Revision de Frenos'), 
    (SELECT UUID_estado FROM EstadoAsignarOrden WHERE Nombre = 'En proceso'), 
    'Carro de Paulo', '25/08/2024', '30/08/2024', 'Reparación de frenos'
);


INSERT INTO AsignarOrden (UUID_AsignarOrden, UUID_cita, UUID_servicio, UUID_estado,Carro_Empleado, FechaAsignacion, FechaFinalizacion, Descripcion)
  VALUES (SYS_GUID(), 
          (SELECT UUID_cita FROM Cita WHERE Descripcion = 'Reparacion de sistema eléctrico'), 
          (SELECT UUID_servicio FROM Servicio WHERE Nombre = 'Cambio de Bateria'), 
          (SELECT UUID_estado FROM EstadoAsignarOrden WHERE Nombre = 'Terminado'), 'Carro de La fuente',
          '25/08/2024', '30/08/2024', 'Cambio de bateria y llantas');



------------------------------------------------------------------------------------------------------------------------

INSERT INTO Factura (UUID_factura, FacturaIdentificacion, FechaEmision, FechaVencimiento) 
VALUES (SYS_GUID(), 'Carlos', '2024-10-13','2024-10-20');

INSERT INTO Factura (UUID_factura, FacturaIdentificacion, FechaEmision, FechaVencimiento) 
VALUES (SYS_GUID(), 'Manuel', '2024-10-12','2024-10-19');
------------------------------------------------------------------------------------------------------------------------


INSERT INTO DetalleFactura (UUID_DetalleFactura ,UUID_factura ,UUID_productoRepuesto ,UUID_AsignarOrden )
VALUES (SYS_GUID(),
        (Select UUID_factura from Factura where FacturaIdentificacion = 'Carlos' ),
        (Select UUID_productoRepuesto from ProductoRepuesto where Nombre = 'Aceite de motor'),
        (Select UUID_AsignarOrden from AsignarOrden where Carro_Empleado = 'Carro de Carlos'));
        
    
INSERT INTO DetalleFactura (UUID_DetalleFactura ,UUID_factura ,UUID_productoRepuesto ,UUID_AsignarOrden )
VALUES (SYS_GUID(),
        (Select UUID_factura from Factura where FacturaIdentificacion = 'Manuel' ),
        (Select UUID_productoRepuesto from ProductoRepuesto where Nombre = 'Filtro de aire'),
        (Select UUID_AsignarOrden from AsignarOrden where Carro_Empleado = 'Carro de Lionel'));
------------------------------------------------------------------------------------------------------------------------

SELECT  CategoriaItem.Nombre AS "Categoria", ProductoRepuesto.Nombre, 
ProductoRepuesto.ImagenProductoRepuesto AS Imagen, ProductoRepuesto.Precio
FROM ProductoRepuesto
INNER JOIN CategoriaItem
ON ProductoRepuesto.UUID_item = CategoriaItem.UUID_item ; 

Select Empleado.Nombre AS "Tarea asignada a:", Cliente.Dui_cliente AS "Cliente", Modelo.Nombre "Modelo de carro a revisar", Servicio.Nombre AS "Servicio a realizar:", 
EstadoAsignarOrden.Nombre As "Estado de la tarea:", AsignarOrden.FechaAsignacion AS "Fecha de asignacion", 
AsignarOrden.FechaFinalizacion AS "Fecha de finalizacion", AsignarOrden.Descripcion
FROM AsignarOrden 
INNER JOIN Cita ON AsignarOrden.UUID_cita = Cita.UUID_cita      
INNER JOIN Servicio ON AsignarOrden.UUID_servicio = Servicio.UUID_servicio
INNER JOIN EstadoAsignarOrden ON AsignarOrden.UUID_estado = EstadoAsignarOrden.UUID_estado
INNER JOIN Empleado ON Cita.Dui_Empleado = Empleado.Dui_Empleado
INNER JOIN Cliente ON Cita.Dui_cliente = Cliente.Dui_cliente
INNER JOIN Carro ON Cliente.Dui_cliente = Carro.Dui_cliente
INNER JOIN Modelo ON Carro.UUID_modelo = Modelo.UUID_modelo;





Select AsignarOrden.UUID_AsignarOrden, Servicio.Nombre AS "Servicio a realizar", AsignarOrden.FechaAsignacion AS "Fecha de inicio", AsignarOrden.FechaFinalizacion AS "Fecha de finalizacion", EstadoAsignarOrden.Nombre As "Estado de la tarea", AsignarOrden.Descripcion FROM AsignarOrden INNER JOIN Cita ON AsignarOrden.UUID_Cita = Cita.UUID_Cita INNER JOIN Empleado ON Cita.Dui_empleado = Empleado.Dui_empleado INNER JOIN Usuario ON Empleado.UUID_usuario = Usuario.UUID_usuario INNER JOIN Cliente ON Cita.Dui_cliente = Cliente.Dui_cliente INNER JOIN Servicio ON AsignarOrden.UUID_servicio = Servicio.UUID_servicio INNER JOIN EstadoAsignarOrden ON AsignarOrden.UUID_estado = EstadoAsignarOrden.UUID_estado Where Usuario.CorreoElectronico = 'maria.dominguez@gmail.com';

SELECT Carro.Placa_carro AS "Placa_Carro", Marca.Nombre AS "Marca_Carro", Modelo.Nombre AS "Modelo_Carro", Carro.Ano AS "AÃ±o_Carro", Carro.Color AS "Color_Carro", Carro.ImagenCarro AS "IMAGEN", Carro.FechaRegistro, Carro.Dui_Cliente, Carro.Descripcion FROM Cliente INNER JOIN Carro ON Cliente.Dui_cliente = Carro.Dui_cliente INNER JOIN Modelo ON Carro.UUID_modelo = Modelo.UUID_modelo INNER JOIN Marca ON Modelo.UUID_marca = Marca.UUID_marca WHERE Cliente.Correo_Electronico = 'goncho@gmail.com';

SELECT Cita.UUID_cita, Cliente.Nombre AS "Cliente", Empleado.Nombre AS "Empleaado", Cita.Fecha_cita AS "Fecha", Cita.Hora_cita AS "Hora", Cita.Descripcion FROM Cita INNER JOIN Cliente ON Cita.Dui_cliente = Cliente.Dui_cliente INNER JOIN Empleado ON Cita.Dui_empleado = Empleado.Dui_empleado ;

SELECT ProductoRepuesto.UUID_productoRepuesto, ProductoRepuesto.Nombre, ProductoRepuesto.ImagenProductoRepuesto, CategoriaItem.Nombre AS CategoriaNombre, ProductoRepuesto.Precio FROM ProductoRepuesto INNER JOIN CategoriaItem ON ProductoRepuesto.UUID_item = CategoriaItem.UUID_item ORDER BY ProductoRepuesto.Nombre;

SELECT Carro.Placa_carro AS Placa_carro,Cliente.Nombre AS Cliente,Modelo.Nombre AS Modelo, Carro.Color AS Color,Carro.Ano AS Anio,Carro.ImagenCarro AS ImagenCarro, Carro.FechaRegistro AS FechaRegistro,Carro.Descripcion AS DescripcionCarro FROM Carro Inner Join Cliente on Carro.Dui_cliente = Cliente.Dui_cliente INNER JOIN Modelo on Carro.UUID_modelo = Modelo.UUID_modelo;

SELECT ProductoRepuesto.UUID_productoRepuesto, ProductoRepuesto.Nombre, ProductoRepuesto.ImagenProductoRepuesto, CategoriaItem.Nombre AS CategoriaNombre, ProductoRepuesto.Precio FROM ProductoRepuesto INNER JOIN CategoriaItem ON ProductoRepuesto.UUID_item = CategoriaItem.UUID_item; 
 
SELECT 
    Empleado.Dui_empleado,
    Empleado.Nombre,
    Empleado.Apellido,
    Empleado.ImagenEmpleado,
    Empleado.FechaNacimiento,
    Empleado.Telefono,
    Usuario.CorreoElectronico,
    Usuario.Contrasena
FROM 
    Usuario
INNER JOIN 
    Empleado 
ON 
    Usuario.UUID_usuario = Empleado.UUID_usuario;
    
SELECT 
    Empleado.Dui_empleado,
    Empleado.Nombre,
    Empleado.Apellido,
    Empleado.ImagenEmpleado,
    Empleado.FechaNacimiento,
    Empleado.Telefono,
    Usuario.CorreoElectronico,
    Usuario.Contrasena
FROM 
    Empleado
INNER JOIN 
    Usuario 
ON 
    Empleado.UUID_usuario = Usuario.UUID_usuario
INNER JOIN 
    Rol 
ON 
    Usuario.UUID_rol = Rol.UUID_rol
WHERE 
    Rol.Nombre = 'Administrador';
    
commit;

select * from detalleFactura;


SELECT AsignarOrden.UUID_AsignarOrden, Empleado.Nombre AS "Asignacion para", Servicio.Nombre AS "Servicio a realizar", EstadoAsignarOrden.Nombre AS "Estado del servicio", AsignarOrden.Carro_Empleado AS "Carro del cliente a ser revisado", AsignarOrden.FechaAsignacion AS "Fecha de asignacion", AsignarOrden.FechaFinalizacion AS "Fecha de finalizacion", AsignarOrden.Descripcion FROM AsignarOrden INNER JOIN Cita ON AsignarOrden.UUID_cita = Cita.UUID_cita INNER JOIN Servicio ON AsignarOrden.UUID_servicio = Servicio.UUID_servicio INNER JOIN EstadoAsignarOrden ON AsignarOrden.UUID_estado = EstadoAsignarOrden.UUID_estado INNER JOIN Empleado ON Cita.Dui_Empleado = Empleado.Dui_Empleado;
//WHERE Empleado.Nombre LIKE ? OR Servicio.Nombre LIKE ? OR AsignarOrden.Descripcion LIKE ? ORDER BY AsignarOrden.FechaAsignacion DESC";

select cita.UUID_cita ,Empleado.nombre,Cita.Fecha_cita,Cita.Hora_cita from cita inner join Empleado on Cita.Dui_empleado = Empleado.Dui_empleado;

select AsignarOrden.UUID_asignarOrden, Servicio.Nombre, AsignarOrden.Carro_Empleado from AsignarOrden Inner Join Servicio on AsignarOrden.UUID_servicio = Servicio.UUID_servicio;

SELECT Cita.UUID_cita, Cliente.Nombre AS Cliente, Empleado.Nombre AS Empleado, Cita.Fecha_cita AS Fecha, Cita.Hora_cita AS  Hora, Cita.Descripcion FROM Cita INNER JOIN Cliente ON Cita.Dui_cliente = Cliente.Dui_cliente INNER JOIN Empleado ON Cita.Dui_empleado = Empleado.Dui_empleado;

SELECT DetalleFactura.UUID_detalleFactura, Factura.FacturaIdentificacion AS "Factura de", ProductoRepuesto.Nombre AS "Producto o repuesto", 
Servicio.Nombre AS "Servicio" 
FROM DetalleFactura 
INNER JOIN Factura ON DetalleFactura.UUID_factura = Factura.UUID_factura 
INNER JOIN ProductoRepuesto ON DetalleFactura.UUID_productoRepuesto = ProductoRepuesto.UUID_productoRepuesto
INNER JOIN AsignarOrden ON DetalleFactura.UUID_asignarOrden = AsignarOrden.UUID_asignarOrden 
INNER JOIN Servicio ON AsignarOrden.UUID_servicio = Servicio.UUID_servicio;

Select ProductoRepuesto.UUID_productoRepuesto, ProductoRepuesto.Nombre from ProductoRepuestoñ

