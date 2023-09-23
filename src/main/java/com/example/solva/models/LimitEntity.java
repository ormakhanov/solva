package com.example.solva.models;

import com.example.solva.dto.LimitDTO;
import com.example.solva.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "limits")
public class LimitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "limit_id", nullable = false)
    private Long limitId;
    @Column(name = "user_account")
    private String userAccount;
    @Column(name = "limit_currency_shortname")
    private String limitCurrencyShortname = "USD";
    @Column(name = "limit_sum")
    private BigDecimal limitSum;
    @Column(name = "limit_balance")
    private BigDecimal limitBalance;
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;
    @Column(name = "limit_datetime")
    private String limitDatetime;

    @OneToMany(targetEntity = TransactionEntity.class, mappedBy = "transactionId", orphanRemoval = false, fetch = FetchType.LAZY)
    List<TransactionEntity> transactionEntityList;

    public LimitEntity(String userAccount, CategoryEnum category, String limitDatetime, BigDecimal limitSum, BigDecimal limitBalance) {
        this.userAccount = userAccount;
        this.category = category;
        this.limitDatetime = limitDatetime;
        this.limitSum = limitSum;
        this.limitBalance = limitBalance;
    }

    public LimitDTO toDTO() {
        return new LimitDTO(
                userAccount,
                limitCurrencyShortname,
                limitSum.doubleValue(),
                limitBalance.doubleValue(),
                category,
                limitDatetime
        );

    }
}
