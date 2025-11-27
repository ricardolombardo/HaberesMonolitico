CREATE PROCEDURE PRC_GEN_TABULADOS
    @idLiquidacion INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @anio INT, @mes INT;
    DECLARE @fechaInicio DATE, @fechaFin DATE;

    -- Obtener año y mes desde la tabla Liquidacion
    SELECT @anio = L.anio, @mes = L.mes
    FROM LIQUIDACION L
    WHERE L.ID = @idLiquidacion;

    -- Calcular fecha
    SET @fechaInicio = DATEFROMPARTS(@anio, @mes, 1);

    -- Insertar en Tabulado
    INSERT INTO TABULADO (ID_LIQUIDACION, ID_NOU)
    SELECT @idLiquidacion, NOU.ID
    FROM PERSONA P
    JOIN NOU NOU ON NOU.ID_PERSONA = P.ID
    JOIN EVENTO_NOU EN ON EN.NOU_ID = NOU.ID
    JOIN EVENTO E ON E.ID = EN.EVENTO_ID
    WHERE EN.FECHA_INICIO <= @fechaInicio
      AND EN.FECHA_FIN > @fechaInicio
      AND E.IDENTIFICADOR = 'PlantaPermanente';
END;
