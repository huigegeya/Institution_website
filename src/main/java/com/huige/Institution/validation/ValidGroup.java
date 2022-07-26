package com.huige.Institution.validation;

import javax.validation.groups.Default;

public interface ValidGroup extends Default {
    interface Crud extends ValidGroup {
        interface Create extends Crud {

        }

        interface Update extends Crud {

        }

        interface Read extends Crud {

        }

        interface Delete extends Crud {

        }

    }
}
