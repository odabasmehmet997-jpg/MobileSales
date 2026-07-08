package org.simpleframework.xml.core;


class SessionManager {
    private final ThreadLocal<Reference> local = new ThreadLocal<>();
    public Session open() throws Exception {
        return this.open(true);
    }
    public Session open(final boolean z) throws Exception {
        final Reference reference = local.get();
        if (null != reference) {
            return reference.get();
        }
        return this.create(z);
    }
    private Session create(final boolean z) throws Exception {
        final Reference reference = new Reference(z);
        local.set(reference);
        return reference.get();
    }
    public void close() throws Exception {
        final Reference reference = local.get();
        if (null == reference) {
            throw new PersistenceException("Session does not exist");
        }
        if (0 == reference.clear()) {
            local.remove();
        }
    }
    private static class Reference {
        private int count;
        private final Session session;

        public Reference(final boolean z) {
            session = new Session(z);
        }

        public Session get() {
            final int i2 = count;
            if (0 <= i2) {
                count = i2 + 1;
            }
            return session;
        }

        public int clear() {
            final int i2 = count - 1;
            count = i2;
            return i2;
        }
    }
}
