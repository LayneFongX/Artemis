package com.farid.artemis.domain.base;

import com.farid.artemis.utils.NoNullFieldStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class BaseDO implements Serializable {

    private static final long serialVersionUID = 8702872432337844438L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, new NoNullFieldStringStyle());
    }

}