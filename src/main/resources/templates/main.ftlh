<#import "/parts/common.ftlh" as c>
<#import "/parts/login.ftlh" as l>

<@c.page>
<div>
    <@l.logout />
    <span><a href="/user">User List</a></span>
</div>
<div>
    <form method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="text" name="text" placeholder="Enter message:"/>
        <input type="text" name="tag" placeholder="Enter tag:"/>
        <button type="submit">Add message</button>
    </form>
</div>
<div>Message List:</div>
<form method="get" action="/main">
    <input type="text" name="tagFilter" placeholder="filter:"/>
    <button type="submit">Search</button>
</form>
<#list messages as message>
<div>
    <b>${message.id}</b>
    <span>${message.text}</span>
    <i>${message.tag}</i>
    <strong>${message.authorName}</strong>
</div>
<#else>
    No messages
</#list>
</@c.page>