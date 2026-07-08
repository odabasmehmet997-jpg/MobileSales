package org.simpleframework.xml.transform;


class CharacterTransform implements Transform<Character> {
    CharacterTransform() {
    }

    
    @Override // org.simpleframework.xml.transform.Transform
    public Character read(final String str) throws Exception {
        if (1 != str.length()) {
            throw new InvalidFormatException("Cannot convert '%s' to a character", str);
        }
        return Character.valueOf(str.charAt(0));
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final Character ch) throws Exception {
        return ch.toString();
    }
}
