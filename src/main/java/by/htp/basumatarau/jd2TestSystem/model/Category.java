package by.htp.basumatarau.jd2TestSystem.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcategory")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Question> questions;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey,
            nullable = true,
            name = "categories_idcategory",
            referencedColumnName = "idcategory")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory",
            cascade = {CascadeType.ALL},
            orphanRemoval = true)
    private Set<Category> childCategories;

    public void addChildCategory(Category category){
        getChildCategories().add(category);
        category.setParentCategory(this);
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<Category> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(Set<Category> childCategories) {
        this.childCategories = childCategories;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
