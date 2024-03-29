package com.aline.core.model;

import com.aline.core.listener.CreateMemberListener;
import com.aline.core.model.account.Account;
import com.aline.core.model.account.Card;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EntityListeners(CreateMemberListener.class)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Branch branch;

    @Column(unique = true)
    private String membershipId;

    @OneToOne(optional = false)
    private Applicant applicant;

    @ManyToMany(fetch = FetchType.LAZY) // Changed fetch type to lazy
    @JoinTable(
            name = "account_holder",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    @JsonManagedReference
    @ToString.Exclude
    private Set<Account> accounts;

    @OneToMany(fetch = FetchType.LAZY) // Changed fetch type to lazy
    @JoinTable(
            name = "card_holder",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    @JsonManagedReference
    @ToString.Exclude
    private Set<Card> cards;

    // Constructors
    public Member(Branch branch, String membershipId, Applicant applicant, Set<Account> accounts) {
        this.branch = branch;
        this.membershipId = membershipId;
        this.applicant = applicant;
        this.accounts = accounts;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }
}