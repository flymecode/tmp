package com.xupt.tmp.enums;

import com.xupt.tmp.exception.RoleTypeException;

public enum RoleEnum {
    STUDENT("student",1),
    TEACHER("teacher",2),
    ADMIN("admin",0);

    private String name;
    private int type;

    RoleEnum(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    /**
     * 获得权限
     */
    public String getRoleName(int code) throws RoleTypeException {
        for (RoleEnum value : RoleEnum.values()) {
            if (value.getType() == code) {
                return value.getName();
            }
        }
        throw new RoleTypeException("没有权限");
    }
}
