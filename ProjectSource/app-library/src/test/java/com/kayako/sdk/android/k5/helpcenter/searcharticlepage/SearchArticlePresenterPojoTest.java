package com.kayako.sdk.android.k5.helpcenter.searcharticlepage;

import com.aurea.unittest.commons.pojo.Testers;
import com.aurea.unittest.commons.pojo.chain.TestChain;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;

import org.junit.Test;

import javax.annotation.Generated;

@Generated("GeneralPatterns")
public class SearchArticlePresenterPojoTest {

    @Test
    public void test_validate_SearchArticlePresenter_Constructors() {
        Validator validator = TestChain.startWith(Testers.constructorTester()).buildValidator();
        validator.validate(PojoClassFactory.getPojoClass(SearchArticlePresenter.class));
    }
}