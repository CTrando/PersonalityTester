#version 120

varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform sampler2D u_distortion_map;
uniform sampler2D u_normal_map;
uniform float u_time;

const float strength = .2f;

vec2 setV_TexCoords(float modifier) {
    return vec2(v_texCoords.x + modifier, v_texCoords.y+ modifier);
}

void main() {
    vec4 texture_color = texture2D(u_texture, v_texCoords);
    float alpha = texture_color.a;

    vec4 temp = mix(texture_color, strength*texture2D(u_distortion_map, setV_TexCoords(u_time)), .3);
    temp.a = alpha;

    vec3 normal_vector = normalize(texture2D(u_normal_map, setV_TexCoords(0))).xyz;
    vec3 i_vector = vec3(1, 0, 0);
    float brightness = (dot(normal_vector, i_vector));
    //automatic clamping to 255 and 1 for rgb and alpha
    gl_FragColor = vec4(temp.x + brightness, temp.y + brightness, temp.z + brightness, alpha);
}