# Kooparts

Kooparts is REST API testing tool.

# Sample

```yaml
version: 1.0
name: Sample Workflow

tests:
  - name: Example Test
    steps:
      - name: GET request step
        http:
          url: "https://jsonplaceholder.typicode.com/todos/1"
          method: GET
          check:
            status: /^20/
```

# Dependency

Kooparts depend on following libraries.

- [Ktor Client](https://ktor.io/docs/welcome.html)
- [yamlkt](https://github.com/Him188/yamlkt)
