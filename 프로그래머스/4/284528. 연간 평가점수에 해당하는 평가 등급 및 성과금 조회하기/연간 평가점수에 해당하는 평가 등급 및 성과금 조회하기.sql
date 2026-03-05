# 사원별 성과금
# 점수 별로 성과금 비율이 다름
# 사번, 성명, 평가 등급, 성과금

WITH A AS(
    SELECT 
        he.EMP_NO,
        he.EMP_NAME,
        hg.SCORE,
        he.SAL
    FROM HR_EMPLOYEES he
    INNER JOIN HR_GRADE hg
    ON he.EMP_NO = hg.EMP_NO
)

SELECT 
    EMP_NO,
    EMP_NAME,
    CASE
        WHEN 96 <= AVG(SCORE) THEN 'S'
        WHEN 90 <= AVG(SCORE) THEN 'A'
        WHEN 80 <= AVG(SCORE) THEN 'B'
        ELSE 'C'
    END AS GRADE,
    CASE
        WHEN 96 <= AVG(SCORE) THEN SAL * 0.2
        WHEN 90 <= AVG(SCORE) THEN SAL * 0.15
        WHEN 80 <= AVG(SCORE) THEN SAL * 0.1
        ELSE 0
    END AS BONUS
FROM A
GROUP BY EMP_NO, EMP_NAME
ORDER BY EMP_NO ASC;