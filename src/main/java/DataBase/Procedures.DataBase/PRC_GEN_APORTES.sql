IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'PRC_GEN_APORTES')
BEGIN
    DROP PROCEDURE PRC_GEN_APORTES;
END
GO

CREATE PROCEDURE PRC_GEN_APORTES
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
	WHERE CODIGO = 'A0001';

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
		SUM(CASE WHEN C.REMUNERATIVO = 1 THEN TC.MONTO * 0.08 ELSE 0 END) AS TOTAL_APORTES,
		'A'
	FROM PERSONA P
	JOIN NOU NOU ON NOU.ID_PERSONA = P.ID
	JOIN EVENTO_NOU EN ON EN.NOU_ID = NOU.ID
	JOIN EVENTO E ON E.ID = EN.EVENTO_ID
	JOIN TABULADO T ON T.ID_NOU = NOU.ID
	JOIN TABULADO_CONCEPTO TC ON TC.ID_TABULADO = T.ID
	JOIN CONCEPTO C ON TC.ID_CONCEPTO = C.ID
	WHERE EN.FECHA_INICIO <= @fechaInicioLiquidacion
	  AND EN.FECHA_FIN > @fechaInicioLiquidacion
	  AND T.ID_LIQUIDACION = @idLiquidacion
	  AND E.IDENTIFICADOR = 'APORTES'
	GROUP BY T.ID;
END;
