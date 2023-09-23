create table limits
(
    limit_id           bigserial primary key,
    user_account       varchar(100) not null,
    limit_currency_shortname varchar,
    limit_sum      decimal,
    limit_balance      decimal,
    category     varchar,
    limit_datetime varchar
);

create table currency
(
    currency_type           varchar primary key,
    close          decimal,
    previous_close decimal
);

create table transactions
(
    transaction_id     bigserial primary key,
    account_from       varchar(10) not null,
    account_to         varchar(10) not null,
    currency_shortname varchar     not null,
    sum                decimal,
    expense_category   varchar     not null,
    datetime           varchar,
    limit_exceeded     boolean,
    limit_id           bigint references limits (limit_id)
);


