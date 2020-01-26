const fastify = require('fastify')({ logger: false })

fastify.route({
    method: 'GET',
    url: '/description',
    schema: {
        querystring: {
            description: { type: 'string' }
        },
        response: {
            200: {
                type: 'object',
                properties: {
                    description: { type: 'string' },
                    word: { type: 'string' }
                }
            }
        }
    },
    // this function is executed for every request before the handler is executed
    preHandler: async (request, reply) => {
        // E.g. check authentication
        // We have no authentication. Yet.
    },
    handler: async (request, reply) =>
    {
        var spawn = require('child_process').spawn,
            py = spawn('python', ["./pythontest.py", request.query.description]);       

        var result = '';
        py.stdout.on('data', function (data) {
            reply.send({ description: request.query.description, word: data.toString()});
        })
    }
})

const start = async () => {
    try {
        await fastify.listen(3000)
    } catch (err) {
        process.exit(1)
    }
}
start()


