SELECT *
FROM recipe
WHERE id IN (SELECT recipe_id
             FROM recipe_ingredient
             GROUP BY recipe_id
             HAVING COUNT(*) BETWEEN 1 AND 7)
  AND id IN (SELECT recipe_id
             FROM recipe_steps
             GROUP BY recipe_id
             HAVING COUNT(*) BETWEEN 1 AND 2)
  AND id IN (SELECT recipe_id
             FROM recipe_ingredient
             WHERE ingredient_id IN ('e7b2c1f8-8a1b-4e3b-a0d9-1f2b3c4d5e6f', 'd5e6f7a8-9012-34bc-def0-567890abcdef'))
  AND id NOT IN (SELECT recipe_id
                 FROM recipe_ingredient
                 WHERE ingredient_id IN ());

select *
from ingredient;
select *
from recipe;
select *
from recipe_ingredient;


SELECT *
FROM recipe
WHERE id IN (SELECT recipe_id
             FROM recipe_ingredient
             GROUP BY recipe_id
             HAVING (:minIngredientCount IS NULL OR COUNT(*) >= :minIngredientCount)
                AND (:maxIngredientCount IS NULL OR COUNT(*) <= :maxIngredientCount))
  AND id IN (SELECT recipe_id
             FROM recipe_steps
             GROUP BY recipe_id
             HAVING (:minStepsCount IS NULL OR COUNT(*) >= :minStepsCount)
                AND (:maxStepsCount IS NULL OR COUNT(*) <= :maxStepsCount))
  AND id NOT IN (SELECT recipe_id
                 FROM recipe_ingredient
                 WHERE ingredient_id IN :excludedIngredientsIds)