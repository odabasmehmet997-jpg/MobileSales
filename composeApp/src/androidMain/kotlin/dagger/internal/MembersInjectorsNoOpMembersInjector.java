package dagger.internal;

import dagger.MembersInjector;

enum MembersInjectorsNoOpMembersInjector implements MembersInjector<Object> {
    INSTANCE;
    public void injectMembers(final Object obj) {
        Preconditions.checkNotNull(obj, "Cannot inject members into a null reference");
    }
}
