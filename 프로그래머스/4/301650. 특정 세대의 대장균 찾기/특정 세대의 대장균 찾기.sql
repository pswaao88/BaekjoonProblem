WITH RECURSIVE TREE AS (
    -- 루트 부분으로 1회만 실행
    SELECT 
        ID, 
        PARENT_ID , 
        1 AS DEPTH 
    FROM ECOLI_DATA WHERE PARENT_ID IS NULL
    -- 이 아래부터 재귀 파트
    UNION ALL
    -- root부터 시작해서 재귀 돌리기
    -- e는 자식 t가 부모의 역할
    SELECT 
        e.ID, 
        e.PARENT_ID, 
        t.DEPTH + 1
    FROM ECOLI_DATA e
    INNER JOIN 
    TREE t ON e.PARENT_ID = t.ID
)

SELECT ID FROM TREE WHERE DEPTH = 3;