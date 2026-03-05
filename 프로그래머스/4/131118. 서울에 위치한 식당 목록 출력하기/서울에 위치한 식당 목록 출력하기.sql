# 서울에 위치한 식당
# 식당 ID, 음식 종류, 즐겨찾기 수, 주소, 리뷰 평균 점수(ROUNT(, 2)
# 평균점수로 내림 차순, 즐겨찾기 내림차순
WITH A AS (
    SELECT  
        REST_ID,
        REST_NAME,
        FOOD_TYPE,
        FAVORITES,
        ADDRESS
    FROM REST_INFO
    WHERE ADDRESS LIKE '서울%'
), B AS (
    SELECT
        REST_ID,
        ROUND(AVG(REVIEW_SCORE), 2) AS SCORE
    FROM REST_REVIEW
    GROUP BY REST_ID
)
SELECT
    a.REST_ID,
    a.REST_NAME,
    a.FOOD_TYPE,
    a.FAVORITES,
    a.ADDRESS,
    b.SCORE
FROM A a
INNER JOIN B b
ON a.REST_ID = b.REST_ID
ORDER BY b.SCORE DESC, a.FAVORITES DESC;