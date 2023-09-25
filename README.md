Чтобы запустить:
1. запустите docker-compose.yml
2. запустите SolvaApplication

Откройте Swagger-ui:
http://localhost:8090/swagger-ui/index.html

Endpoints:
1. http://localhost:8090/limits/{account} - Возвращает актуальные лимиты для каждой категории

2. http://localhost:8090/limits/update - Обновляет лимит
{
"account": "string",
"accountLimit": 0,
"category": "product"
}

3. http://localhost:8090/limits/create - Создает лимит
{
"account": "string",
"category": "product"
}

4. http://localhost:8090/transactions/{account} - Возвращает транзакции

5. http://localhost:8090/transactions/create - Создает транзакции
   {
   "accountFrom": "string",
   "accountTo": "string",
   "category": "product",
   "currencyShortname": "KZT",
   "sum": 0
   }

6. http://localhost:8090/transactions/limitExceeded/{account} - Возвращает транзакции с лимитами, у которых остаток месячного лимита ниже нуля


