IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'PRC_GEN_ANTIGUEDAD')
BEGIN
    DROP PROCEDURE PRC_GEN_ANTIGUEDAD;
END
GO

CREATE PROCEDURE PRC_GEN_ANTIGUEDAD
    @idLiquidacion INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @anio INT, @mes INT;
    DECLARE @fechaInicioLiquidacion DATE;
	DECLARE @idConcepto INT;

	-- Obtener el ID del concepto
	SELECT @idConcepto = ID
	FROM CONCEPTO
	WHERE CODIGO = 'D0005';

    -- Obtener año y mes desde la tabla Liquidacion
    SELECT @anio = L.ANIO, @mes = L.MES
    FROM LIQUIDACION L
    WHERE L.ID = @idLiquidacion;

    -- Calcular fecha
    SET @fechaInicioLiquidacion = DATEFROMPARTS(@anio, @mes, 1);

    -- Insertar en TabuladoConcepto
	INSERT INTO TABULADO_CONCEPTO (ID_TABULADO, ID_CONCEPTO, MONTO, SENTIDO)
	SELECT 
		T.ID,
		@idConcepto,
		P.ANTIGUEDAD * J.MONTO / 100,
		'D'
	FROM PERSONA P
	JOIN NOU NOU ON NOU.ID_PERSONA = P.ID
	JOIN EVENTO_NOU EN ON EN.NOU_ID = NOU.ID
	JOIN EVENTO E ON E.ID = EN.EVENTO_ID
	JOIN TABULADO T ON T.ID_NOU = NOU.ID
	JOIN JERARQUIA J ON J.ID = P.ID_JERARQUIA
	WHERE EN.FECHA_INICIO <= @fechaInicioLiquidacion
	  AND EN.FECHA_FIN > @fechaInicioLiquidacion
	  AND T.ID_LIQUIDACION = @idLiquidacion
	  AND E.IDENTIFICADOR = 'ANTIGUEDAD';
END;
