package com.monkey.util;

import com.monkey.entity.Fragment;

public class FragmentUtil {
    public static String concat(Iterable<Fragment> fragments) {
        StringBuilder builder = new StringBuilder();
        for (Fragment fragment : fragments) {
            if (fragment.getFType()) {
                builder.append(fragment.getFContent());
            }
            else {
                // Constraint: if fragment.fType is false, fragment.fContent must be a integer string
                int lenToDel = Integer.valueOf(fragment.getFContent());
                builder.delete(builder.length() - lenToDel - 1, builder.length());
            }
        }
        return builder.toString();
    }
    public static Fragment nest(Fragment wrapper, Fragment content, int offset) {
        assert wrapper.getUserId().equals(content.getUserId());
        String original = wrapper.getFContent();
        StringBuilder builder = new StringBuilder();
        builder.append(original.substring(0, offset));
        if (content.getFType())
            builder.append(content.getFContent());
        else {
            int lenToDel = Integer.valueOf(content.getFContent());
            builder.delete(builder.length() - lenToDel - 1, builder.length());
        }
        builder.append(original.substring(offset));
        Fragment result = new Fragment();
        result.updateFrom(wrapper);
        result.setFContent(builder.toString());
        return result;
    }
    public static Fragment[] insert(Fragment original, Fragment content, int offset) {
        assert !original.getUserId().equals(content.getUserId());
        Fragment left = new Fragment();
        left.updateFrom(original);
        left.setFContent(original.getFContent().substring(0, offset));
        Fragment right = new Fragment();
        right.updateFrom(original);
        right.setFContent(original.getFContent().substring(offset));
        return new Fragment[] { left, content, right };
    }
}
