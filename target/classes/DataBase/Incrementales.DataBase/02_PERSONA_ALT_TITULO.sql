BEGIN
    -- 1. Agregar la columna ID_Titulo si no existe
    IF NOT EXISTS (
        SELECT 1 
        FROM sys.columns 
        WHERE Name = N'ID_Titulo'
          AND Object_ID = Object_ID(N'Persona')
    )
    BEGIN
        ALTER TABLE Persona
        ADD ID_Titulo INT NULL; -- o NOT NULL si querés obligatorio
    END;

    -- 2. Agregar la foreign key si no existe
    IF NOT EXISTS (
        SELECT 1 
        FROM sys.foreign_keys 
        WHERE name = N'FK_Persona_Titulo'
    )
    BEGIN
        ALTER TABLE Persona
        ADD CONSTRAINT FK_Persona_Titulo
        FOREIGN KEY (ID_Titulo) REFERENCES Titulo(ID);
    END;
END;
