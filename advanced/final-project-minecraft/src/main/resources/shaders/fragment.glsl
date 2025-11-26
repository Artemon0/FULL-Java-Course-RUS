#version 330 core

in vec3 vertexColor;
in vec2 texCoord;

out vec4 FragColor;

uniform sampler2D textureSampler;
uniform bool useTexture;

void main() {
    if (useTexture) {
        FragColor = texture(textureSampler, texCoord);
    } else {
        FragColor = vec4(vertexColor, 1.0);
    }
}
