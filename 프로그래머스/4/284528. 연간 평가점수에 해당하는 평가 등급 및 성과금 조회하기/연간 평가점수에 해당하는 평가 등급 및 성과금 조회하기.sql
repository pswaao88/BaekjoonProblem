WITH TEAR_SUM AS (
    SELECT 
        EMP_NO,
        AVG(SCORE) AS SCORE
    FROM HR_GRADE GROUP BY EMP_NO
), TOTAL AS (
    SELECT 
        he.EMP_NO,
        he.EMP_NAME,
        CASE
            WHEN hg.SCORE >= 96 THEN 'S'
            WHEN hg.SCORE >= 90 THEN 'A'
            WHEN hg.SCORE >= 80 THEN 'B'
            ELSE 'C'
        END AS GRADE,
        SAL
    FROM HR_EMPLOYEES he
    INNER JOIN TEAR_SUM hg
    ON he.EMP_NO = hg.EMP_NO
)
SELECT 
    EMP_NO,
    EMP_NAME,
    GRADE,
    CASE
        WHEN GRADE = 'S' THEN SAL * 0.2
        WHEN GRADE = 'A' THEN SAL * 0.15
        WHEN GRADE = 'B' THEN SAL * 0.1
        ELSE SAL * 0
    END AS BONUS
FROM TOTAL
ORDER BY EMP_NO ASC;