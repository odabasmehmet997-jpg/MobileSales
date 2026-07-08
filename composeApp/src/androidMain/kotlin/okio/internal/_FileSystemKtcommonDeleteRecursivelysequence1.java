package okio.internal;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;
import kotlinx.coroutines.CoroutineScope;
import okio.FileSystem;
import okio.Path;

final class _FileSystemKtcommonDeleteRecursivelysequence1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Path>, Continuation<? super Unit>, Object> {
    final Path fileOrDirectory;
    final FileSystem this_commonDeleteRecursively;
    private Object L0;
    int label;
    _FileSystemKtcommonDeleteRecursivelysequence1(final FileSystem fileSystem, final Path path, final Continuation<? super _FileSystemKtcommonDeleteRecursivelysequence1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this_commonDeleteRecursively = fileSystem;
        fileOrDirectory = path;
    }
    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        final _FileSystemKtcommonDeleteRecursivelysequence1 _filesystemkt_commondeleterecursively_sequence_1 = new _FileSystemKtcommonDeleteRecursivelysequence1(this_commonDeleteRecursively, fileOrDirectory, (Continuation<? super _FileSystemKtcommonDeleteRecursivelysequence1>) continuation);
        _filesystemkt_commondeleterecursively_sequence_1.L0 = obj;
        return _filesystemkt_commondeleterecursively_sequence_1;
    }
    public Object invoke(Object p0) {
        return null;
    }
    public Object invoke(final CoroutineScope sequenceScope, final Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return this.create(sequenceScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
    public Object invokeSuspend(final Object obj) {
        final Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        final int i2 = label;
        if (0 == i2) {
            ResultKt.throwOnFailure(obj);
            final SequenceScope sequenceScope = (SequenceScope) L0;
            final FileSystem fileSystem = this_commonDeleteRecursively;
            final ArrayDeque arrayDeque = new ArrayDeque();
            final Path path = fileOrDirectory;
            label = 1;
            try {
                if (_FileSystemKt.collectRecursively(sequenceScope, fileSystem, arrayDeque, path, false, true, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        } else {
            if (1 != i2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
